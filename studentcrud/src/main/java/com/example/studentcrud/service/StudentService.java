package com.example.studentcrud.service;

import com.example.studentcrud.entity.Student;
import com.example.studentcrud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// This class handles all business logic related to Student
@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    // Save a new student (CREATE)
    public Student saveStudent(Student student) {
        student.setId(null); // Ensure ID is null so JPA generates it
        return repo.save(student);
    }

    // Update an existing student (UPDATE)
    public Student updateStudent(Long id, Student updatedStudent) {
        if (id == 0) {
            throw new RuntimeException("Invalid student ID: 0");
        }

        Student existingStudent = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setCourse(updatedStudent.getCourse());

        return repo.save(existingStudent);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    // Get student by ID
    public Optional<Student> getStudentById(Long id) {
        return repo.findById(id);
    }

    // Delete student by ID
    public void deleteStudent(Long id) {
        repo.deleteById(id);
    }
}
