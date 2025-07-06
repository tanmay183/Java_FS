package com.example.studentcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.studentcrud.entity.Student;

// Interface for basic DB operations like save(), findById(), delete(), etc.
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // No implementation needed. Spring Data will generate everything.
}
