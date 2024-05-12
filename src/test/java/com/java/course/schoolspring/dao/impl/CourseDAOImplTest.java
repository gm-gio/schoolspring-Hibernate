package com.java.course.schoolspring.dao.impl;

import com.java.course.schoolspring.dao.CourseDAO;
import com.java.course.schoolspring.model.Course;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
        NamedParameterJdbcTemplate.class,
        CourseDAOImpl.class,
}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"/sql/clear_data.sql", "/sql/sample_data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

public class CourseDAOImplTest {
    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    EntityManager em;

    @Test
    void shouldFindAllCourses() {
        List<Course> courses = courseDAO.findAll();
        assertEquals(10, courses.size());
    }

    @Test
    void shouldFindCourseById() {
        Optional<Course> optionalCourse = courseDAO.findById(10001);
        assertTrue(optionalCourse.isPresent());
        assertEquals("Math", optionalCourse.get().getCourseName());
    }

    @Test
    void shouldCreateNewCourse() {
        Course course = new Course();
        course.setCourseName("Java Course");
        course.setCourseDescription("it's not coffee");
        Course createdCourse = courseDAO.save(course);
        assertNotNull(createdCourse.getCourseId());
        assertEquals("Java Course", createdCourse.getCourseName());
        assertEquals("it's not coffee", createdCourse.getCourseDescription());
    }

    @Test
    void shouldUpdateCourse() {

        Course updateCourse = courseDAO.findById(10001).orElseThrow();
        updateCourse.setCourseName("New Course");
        updateCourse.setCourseDescription("Course Desc");
        courseDAO.save(updateCourse);
        assertEquals("New Course", updateCourse.getCourseName());
        assertEquals("Course Desc", updateCourse.getCourseDescription());
    }


    @Test
    void shouldDeleteCourseById() {
        courseDAO.deleteById(10001);
        List<Course> courses = courseDAO.findAll();
        assertEquals(9, courses.size());
    }


    @Test
    void shouldAddStudentToCourses() {
        int studentId = 10005;
        List<Course> coursesBefore = courseDAO.findCourseByStudentId(studentId);
        assertEquals(4, coursesBefore.size());
        courseDAO.addStudentToCourse(studentId, 10007);
        courseDAO.addStudentToCourse(studentId, 10008);
        List<Course> courses = courseDAO.findCourseByStudentId(studentId);
        assertEquals(6, courses.size());
    }

    @Test
    void shouldDeleteStudentFromCourses() {
        int studentId = 10002;
        List<Course> coursesBefore = courseDAO.findCourseByStudentId(studentId);
        assertEquals(2, coursesBefore.size());
        courseDAO.deleteStudentFromCourse(studentId, 10001);
        courseDAO.deleteStudentFromCourse(studentId, studentId);
        List<Course> courses = courseDAO.findCourseByStudentId(studentId);
        assertTrue(courses.isEmpty());
    }

    @Test
    void shouldAllowDuplicateStudentAdditionToCourse() {
        int studentId = 10005;
        List<Course> coursesBefore = courseDAO.findCourseByStudentId(studentId);
        assertEquals(4, coursesBefore.size());
        courseDAO.addStudentToCourse(studentId, 10007);
        courseDAO.addStudentToCourse(studentId, 10007);
        List<Course> courses = courseDAO.findCourseByStudentId(studentId);
        assertEquals(5, courses.size());
    }

    @Test
    void shouldAllowRepeatedDeletionStudentFromCourse() {
        int studentId = 10005;
        List<Course> coursesBefore = courseDAO.findCourseByStudentId(studentId);
        assertEquals(4, coursesBefore.size());
        courseDAO.deleteStudentFromCourse(studentId, 10001);
        courseDAO.deleteStudentFromCourse(studentId, 10001);
        List<Course> courses = courseDAO.findCourseByStudentId(studentId);
        assertEquals(3, courses.size());
    }


}
