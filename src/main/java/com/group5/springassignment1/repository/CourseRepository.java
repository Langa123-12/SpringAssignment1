package com.group5.springassignment1.repository;

import com.group5.springassignment1.model.Course;
import org.springframework.stereotype.Repository;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Collection;

@Repository
public class CourseRepository {

    // Thread-safe in-memory storage
    private final ConcurrentHashMap<Long, Course> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // Get all courses
    public Collection<Course> findAll() {
        return store.values();
    }

    // Find course by ID
    public Course findById(Long id) {
        return store.get(id);
    }

    // Save new course or update existing
    public Course save(Course course) {
        if (course.getId() == null) {
            course.setId(idGenerator.getAndIncrement());
        }
        store.put(course.getId(), course);
        return course;
    }

    // Delete course by ID
    public boolean deleteById(Long id) {
        return store.remove(id) != null;
    }

    // Clear all data (useful for testing)
    public void clear() {
        store.clear();
        idGenerator.set(1);
    }
}