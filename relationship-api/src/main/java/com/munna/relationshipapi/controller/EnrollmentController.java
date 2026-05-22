package com.munna.relationshipapi.controller;

import com.munna.relationshipapi.entity.Enrollment;
import com.munna.relationshipapi.service.EnrollmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/enrollments")
    public Enrollment createEnrollment(
            @RequestParam Long studentId,
            @RequestParam Long courseId) {

        return enrollmentService.createEnrollment(studentId, courseId);
    }

    @GetMapping("/students/{id}/enrollments")
    public List<Enrollment> getStudentEnrollments(@PathVariable Long id) {
        return enrollmentService.getEnrollmentsByStudent(id);
    }

    @GetMapping("/courses/{id}/enrollments")
    public List<Enrollment> getCourseEnrollments(@PathVariable Long id) {
        return enrollmentService.getEnrollmentsByCourse(id);
    }
}