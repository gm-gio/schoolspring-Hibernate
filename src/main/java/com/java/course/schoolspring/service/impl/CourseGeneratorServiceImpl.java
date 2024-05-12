package com.java.course.schoolspring.service.impl;

import com.java.course.schoolspring.dao.CourseDAO;
import com.java.course.schoolspring.model.Course;
import com.java.course.schoolspring.service.CourseGeneratorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CourseGeneratorServiceImpl implements CourseGeneratorService {


    private final CourseDAO courseDAO;

    public CourseGeneratorServiceImpl(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    final String[] courseNames = {"Math", "Biology", "Physics", "Chemistry", "Computer Science", "Literature", "Art", "Music", "Economics", "History"};
    final String[] courseDescriptions = {"Mathematics course", "Biology course", "Physics course", "Chemistry course", "Computer Science course", "Literature course", "Art course", "Music course", "Economics course", "History course"};

    @Override
    public void generateCoursesIfNeed() {
        if (courseDAO.count() == 0) {
            List<Course> courses = new ArrayList<>();
            for (int i = 0; i < courseNames.length; i++) {
                Course course = new Course(courseNames[i], courseDescriptions[i]);
                courses.add(course);
            }
            courseDAO.saveAll(courses);
        }
    }
}
