package com.example.OnlineExamModule.Controller;

import com.example.OnlineExamModule.Entity.StudentProfileEntity;
import com.example.OnlineExamModule.Service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {
    @Autowired
    private StudentProfileService service;
    // Create a new student profile
    @PostMapping
    public StudentProfileEntity createStudent(@RequestBody StudentProfileEntity student) {
        return service.createStudentProfile(student);
    }

    // Get all students
    @GetMapping
    public List<StudentProfileEntity> getAllStudents() {
        return service.getAllStudentProfiles();
    }

    // Get student by ID
    @GetMapping("/{id}")
    public StudentProfileEntity getStudentById(@PathVariable Long id) {
        return service.getStudentProfileById(id);
    }

    // Update an existing student
    @PutMapping("/{id}")
    public StudentProfileEntity updateStudent(@PathVariable Long id, @RequestBody StudentProfileEntity studentDetails) {
        return service.updateStudentProfile(id, studentDetails);
    }

    // Delete a student
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        service.deleteStudentProfile(id);
        return "Student profile deleted successfully";
    }
}


