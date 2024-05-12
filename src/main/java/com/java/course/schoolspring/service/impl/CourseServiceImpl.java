package com.java.course.schoolspring.service.impl;

import com.java.course.schoolspring.dao.CourseDAO;
import com.java.course.schoolspring.model.Course;
import com.java.course.schoolspring.model.Student;
import com.java.course.schoolspring.service.CourseService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseDAO courseDAO;

    public CourseServiceImpl(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }


    @Override
    public List<Student> findStudentByCourseName(String courseName) {
        return courseDAO.findStudentByCourseName(courseName);
    }

    @Override
    public void addStudentToCourse(Integer studentId, Integer courseId) {
        courseDAO.addStudentToCourse(studentId, courseId);
    }

    @Override
    public void deleteStudentFromCourseService(Integer studentId, Integer courseId) {
        courseDAO.deleteStudentFromCourse(studentId, courseId);
    }

    @Override
    public void createNewCourse(Course course) {
        courseDAO.save(course);
    }

}
