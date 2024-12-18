package com.example.OnlineExamModule.Service;

import com.example.OnlineExamModule.Entity.StudentProfileEntity;
import com.example.OnlineExamModule.Repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileService {
    @Autowired
    private StudentProfileRepository repository;

    // Create a new student profile
    public StudentProfileEntity createStudentProfile(StudentProfileEntity student) {
        if (repository.existsByEmail(student.getEmail())) {
            throw new RuntimeException("Email already exists.");
        }
        return repository.save(student);
    }

    // Get all student profiles
    public List<StudentProfileEntity> getAllStudentProfiles() {
        return repository.findAll();
    }

    // Get a profile by ID
    public StudentProfileEntity getStudentProfileById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    // Update a profile
    public StudentProfileEntity updateStudentProfile(Long id, StudentProfileEntity studentDetails) {
        StudentProfileEntity existing = getStudentProfileById(id);
        existing.setName(studentDetails.getName());
        existing.setEmail(studentDetails.getEmail());
        existing.setPassword(studentDetails.getPassword());
        return repository.save(existing);
    }

    // Delete a student profile
    public void deleteStudentProfile(Long id) {
        repository.deleteById(id);
    }
}
