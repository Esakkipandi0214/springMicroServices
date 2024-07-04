package com.example.school.controller;

import com.example.school.Model.Student;
import com.example.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@CrossOrigin("*")
@RestController
//@RequestMapping(value = "/school")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/student/{id}")
    public ResponseEntity<?> fetchStudentById(@PathVariable String id){
        return studentService.fetchStudentById(id);
    }
    @GetMapping("/student/singleStudent/{id}")
    public ResponseEntity<?> fetchStudentByIdOnly(@PathVariable String id){
        return studentService.fetchStudentByIdOnly(id);
    }

    @GetMapping("/student")
    public ResponseEntity<?> fetchStudents(){
        return studentService.fetchStudents();
    }
    @PostMapping("/student")
    public ResponseEntity<?> createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }
    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student studentDetails) {
        Student updatedStudent = studentService.updateStudent(id, studentDetails);
        return ResponseEntity.ok(updatedStudent);
    }
    @DeleteMapping("/student/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}