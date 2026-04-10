package com.group5.springassignment1.service;

import com.group5.springassignment1.model.Course;
import com.group5.springassignment1.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Collection<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // FIXED: Remove .orElse() since findById returns Course directly
    public Course getCourseById(Long id) {
        return courseRepository.findById(id);  // Direct return, no orElse
    }

    public long countByType(String type) {
        return courseRepository.findAll().stream()
                .filter(course -> course.getType().equalsIgnoreCase(type))
                .count();
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course course) {
        Course existing = courseRepository.findById(id);
        if (existing == null) {
            return null;
        }
        course.setId(id);
        return courseRepository.save(course);
    }

    public boolean deleteCourse(Long id) {
        return courseRepository.deleteById(id);
    }
}