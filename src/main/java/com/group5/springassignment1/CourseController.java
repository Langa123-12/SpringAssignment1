package com.group5.springassignment1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    @GetMapping("/courses")
    public String getCourses() {
        return "2 Foundation courses offered in the Computer Science Department<br>" +
                "5 Undergraduate courses offered in the Computer Science Department<br>" +
                "4 Honours courses offered in the Computer Science Department";
    }
}

