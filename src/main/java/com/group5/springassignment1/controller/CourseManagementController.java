package com.group5.springassignment1.controller;

import com.group5.springassignment1.model.Course;
import com.group5.springassignment1.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseManagementController {

    @Autowired
    private CourseService courseService;

    // Part A endpoint - Course Summary
    @GetMapping("/summary")
    public ResponseEntity<Map<String, Long>> getCourseSummary() {
        Map<String, Long> summary = new HashMap<>();
        summary.put("Foundation courses offered in the Computer Science Department",
                courseService.countByType("Foundation"));
        summary.put("Undergraduate courses offered in the Computer Science Department",
                courseService.countByType("Undergraduate"));
        summary.put("Honours courses offered in the Computer Science Department",
                courseService.countByType("Honours"));
        return ResponseEntity.ok(summary);
    }

    // CREATE - POST
    @PostMapping
    public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) {
        Course created = courseService.createCourse(course);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // READ ALL - GET
    @GetMapping
    public ResponseEntity<Collection<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    // READ ONE - GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }

    // UPDATE - PUT
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody Course course) {
        Course updated = courseService.updateCourse(id, course);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    // DELETE - DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        boolean deleted = courseService.deleteCourse(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    // Validation Error Handler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}