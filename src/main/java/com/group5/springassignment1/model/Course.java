package com.group5.springassignment1.model;

import jakarta.validation.constraints.*;

public class Course {

    private Long id;

    @NotBlank(message = "Course code is required")
    private String courseCode;

    @NotBlank(message = "Course name is required")
    @Size(min = 3, max = 100, message = "Course name must be between 3 and 100 characters")
    private String name;

    @NotNull(message = "Course type is required")
    @Pattern(regexp = "Foundation|Undergraduate|Honours",
            message = "Type must be Foundation, Undergraduate, or Honours")
    private String type;

    @Min(value = 1, message = "Credits must be at least 1")
    @Max(value = 6, message = "Credits must be at most 6")
    private int credits;

    // Constructors
    public Course() {}

    public Course(String courseCode, String name, String type, int credits) {
        this.courseCode = courseCode;
        this.name = name;
        this.type = type;
        this.credits = credits;
    }

    // Getters
    public Long getId() { return id; }
    public String getCourseCode() { return courseCode; }
    public String getName() { return name; }
    public String getType() { return type; }
    public int getCredits() { return credits; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setCredits(int credits) { this.credits = credits; }

    @Override
    public String toString() {
        return "Course{id=" + id + ", courseCode='" + courseCode + "', name='" + name + "', type='" + type + "', credits=" + credits + "}";
    }
}