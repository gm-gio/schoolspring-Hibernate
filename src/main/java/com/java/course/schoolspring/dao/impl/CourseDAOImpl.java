package com.java.course.schoolspring.dao.impl;

import com.java.course.schoolspring.dao.AbstractCrudDao;
import com.java.course.schoolspring.dao.CourseDAO;
import com.java.course.schoolspring.model.Course;
import com.java.course.schoolspring.model.Student;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseDAOImpl extends AbstractCrudDao<Course, Integer> implements CourseDAO {
    public CourseDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public int count() {
        return em.createQuery("SELECT COUNT(c) FROM Course c", Long.class)
                .getSingleResult().intValue();
    }


    @Override
    public List<Student> findStudentByCourseName(String courseName) {
        return em.createQuery("SELECT s FROM Student s " +
                        "JOIN s.courses c " +
                        "WHERE c.courseName = :courseName", Student.class)
                .setParameter("courseName", courseName)
                .getResultList();
    }

    @Override
    public List<Course> findCourseByStudentId(Integer studentId) {
        return em.createQuery("SELECT c FROM Course c " +
                        "JOIN c.students s " +
                        "WHERE s.studentId = :studentId", Course.class)
                .setParameter("studentId", studentId)
                .getResultList();
    }



    @Override
    public void addStudentToCourse(Integer studentId, Integer courseId) {
        Course course = findById(courseId).orElseThrow(() -> new RuntimeException("Course id=%d not found".formatted(courseId)));
        Student student = em.find(Student.class, studentId);
        course.addStudent(student);
        em.merge(course);
    }

    @Override
    public void deleteStudentFromCourse(Integer studentId, Integer courseId) {
        Course course = findById(courseId).orElseThrow(() -> new RuntimeException("Course id=%d not found".formatted(courseId)));
        Student student = em.find(Student.class, studentId);
        if (student != null) {
            course.removeStudent(student);
            em.merge(course);
        }
    }
    @Override
    public void saveAll(List<Course> courses) {
        courses.forEach(this::save);
    }


    @Override
    public Optional<Course> findById(Integer id) {
        return Optional.ofNullable(em.find(Course.class, id));
    }

    @Override
    public List<Course> findAll() {
        return em.createQuery("SELECT c FROM Course c", Course.class)
                .getResultList();
    }


    @Override
    public void deleteById(Integer id) {
        Course course = em.find(Course.class, id);
        if (course != null) {
            em.remove(course);
        }
    }

    public Course update(Course course) {
        return em.merge(course);
    }

}