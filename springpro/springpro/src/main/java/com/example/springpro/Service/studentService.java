package com.example.springpro.Service;

import com.example.springpro.Service.studentService;
import com.example.springpro.Entity.Student;
import com.example.springpro.Repository.studentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class studentService {
    @Autowired
    private studentRepo StudentRepo;

    public Student addSchool(Student student){
        return StudentRepo.save(student);
    }
    public List<Student> fetchSchools(){
        return StudentRepo.findAll();
    }
    public Student fetchSchoolById(int id){
        return StudentRepo.findById(id).orElse(null);
    }
    public Student updateStudent(int id, Student studentDetails) {
        Student student = StudentRepo.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        // Update only the provided fields
        if (studentDetails.getSchoolName() != null) {
            student.setSchoolName(studentDetails.getSchoolName());
        }
        if (studentDetails.getLocation() != null) {
            student.setLocation(studentDetails.getLocation());
        }
        if (studentDetails.getPrincipalName() != null) {
            student.setPrincipalName(studentDetails.getPrincipalName());
        }
        return StudentRepo.save(student);
    }

    public void deleteStudent(int id) {
        StudentRepo.deleteById(id);
    }
}