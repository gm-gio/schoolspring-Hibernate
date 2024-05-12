package com.java.course.schoolspring.service.impl;

import com.java.course.schoolspring.dao.CourseDAO;
import com.java.course.schoolspring.dao.StudentDAO;
import com.java.course.schoolspring.model.Course;
import com.java.course.schoolspring.model.Student;
import com.java.course.schoolspring.service.StudentsCoursesGeneratorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class StudentCourseGeneratorServiceImpl implements StudentsCoursesGeneratorService {


    private StudentDAO studentDAO;
    private CourseDAO courseDAO;

    public StudentCourseGeneratorServiceImpl(StudentDAO studentDAO, CourseDAO courseDAO) {
        this.studentDAO = studentDAO;
        this.courseDAO = courseDAO;
    }

    @Override
    public void assignCoursesToStudents() {

        List<Student> students = studentDAO.findAll();
        List<Course> courses = courseDAO.findAll();

        for (Student student : students) {
            int numCourses = (int) (Math.random() * 3) + 1;
            Collections.shuffle(courses);
            for (int i = 0; i < numCourses && i < courses.size(); i++) {
                Course course = courses.get(i);
                courseDAO.addStudentToCourse(student.getStudentId(), course.getCourseId());
            }
        }
    }
}

