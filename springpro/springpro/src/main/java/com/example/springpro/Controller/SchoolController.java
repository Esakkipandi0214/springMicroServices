package com.example.springpro.Controller;

import com.example.springpro.Entity.Student;
import com.example.springpro.Service.studentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping(value = "/school")
@RestController
public class SchoolController {
    @Autowired
    private studentService StudentService;

    @PostMapping
    public Student addSchool(@RequestBody Student student){
        return StudentService.addSchool(student);
    }
    @GetMapping
    public List<Student> fetchSchools(){
        return  StudentService.fetchSchools();
    }
    @GetMapping("/{id}")
    public Student fetchSchoolById(@PathVariable int id){
        return StudentService.fetchSchoolById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student studentDetails) {
        Student updatedStudent = StudentService.updateStudent(id, studentDetails);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        StudentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}