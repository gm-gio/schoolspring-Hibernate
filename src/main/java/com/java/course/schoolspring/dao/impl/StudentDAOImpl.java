package com.java.course.schoolspring.dao.impl;

import com.java.course.schoolspring.dao.AbstractCrudDao;
import com.java.course.schoolspring.dao.StudentDAO;
import com.java.course.schoolspring.model.Student;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StudentDAOImpl extends AbstractCrudDao<Student, Integer> implements StudentDAO {

    public StudentDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public int count() {
        return em.createQuery("SELECT COUNT(s) FROM Student s", Long.class)
                .getSingleResult().intValue();

    }

    @Override
    public Student create(Student student) {
        em.persist(student);
        return student;
    }


    @Override
    public List<Student> findByCourse(String courseName) {
        return em.createQuery(
                        "SELECT s " +
                                "FROM Student s " +
                                "JOIN s.courses c " +
                                "WHERE c.courseName = :courseName", Student.class)
                .setParameter("courseName", courseName)
                .getResultList();
    }

    @Override
    public void deleteById(Integer id) {
        Student student = em.find(Student.class, id);
        if (student != null) {
            em.remove(student);
        }
    }

    @Override
    public Optional<Student> findById(Integer id) {
        return Optional.ofNullable(em.find(Student.class, id));
    }

    @Override
    public Student update(Student student) {
        return em.merge(student);
    }

    @Override
    public List<Student> findAll() {
        return em.createQuery("SELECT s FROM Student s", Student.class)
                .getResultList();
    }

    @Override
    public void saveAll(List<Student> students) {
        students.forEach(this::create);
    }

}
