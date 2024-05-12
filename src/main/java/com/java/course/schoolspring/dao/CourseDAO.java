package com.java.course.schoolspring.dao;

import com.java.course.schoolspring.model.Course;
import com.java.course.schoolspring.model.Student;

import java.util.List;

public interface CourseDAO extends CrudDAO<Course, Integer> {

    List<Student> findStudentByCourseName(String courseName);

    List<Course> findCourseByStudentId(Integer studentId);

    void addStudentToCourse(Integer studentId, Integer courseId);

    void deleteStudentFromCourse(Integer studentId, Integer courseId);

}
