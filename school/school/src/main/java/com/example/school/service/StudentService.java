package com.example.school.service;

import com.example.school.dto.School;
import com.example.school.dto.StudentResponse;
import com.example.school.Model.Student;
import com.example.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<?> createStudent(Student student){
        try{
            return new ResponseEntity<Student>(studentRepository.save(student), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> fetchStudentById(String id){
        Optional<Student> student =  studentRepository.findById(id);
        if(student.isPresent()){
            School school = restTemplate.getForObject("http://localhost:8080/school/" + student.get().getSchoolId(), School.class);
            StudentResponse studentResponse = new StudentResponse(
                    student.get().getId(),
                    student.get().getName(),
                    student.get().getAge(),
                    student.get().getGender(),
                    school
            );
            return new ResponseEntity<>(studentResponse, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Student Found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> fetchStudentByIdOnly(String id){
        Optional<Student> student =  studentRepository.findById(id);
            return new ResponseEntity<>(student, HttpStatus.OK);
    }


    public ResponseEntity<?> fetchStudents(){
        List<Student> students = studentRepository.findAll();
        if(students.size() > 0){
            return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("No Students",HttpStatus.NOT_FOUND);
        }
    }

    public Student updateStudent(String id, Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Update only the provided fields
        if (studentDetails.getName() != null) {
            student.setName(studentDetails.getName());
        }
        if (studentDetails.getAge() != 0) {
            student.setAge(studentDetails.getAge());
        }
        if (studentDetails.getGender() != null) {
            student.setGender(studentDetails.getGender());
        }
        if (studentDetails.getSchoolId() != null) {
            student.setSchoolId(studentDetails.getSchoolId());
        }

        return studentRepository.save(student);
    }


    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

}