package com.example.springpro.Repository;

import com.example.springpro.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface studentRepo extends JpaRepository<Student,Integer> {
}