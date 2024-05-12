package com.java.course.schoolspring.service;

import com.java.course.schoolspring.model.Course;
import com.java.course.schoolspring.model.Student;

import java.util.List;

public interface CourseService {
    public List<Student> findStudentByCourseName(String courseName);

    public void addStudentToCourse(Integer studentId, Integer courseId);

    public void deleteStudentFromCourseService(Integer studentId, Integer courseId);

    void createNewCourse(Course course);
}
