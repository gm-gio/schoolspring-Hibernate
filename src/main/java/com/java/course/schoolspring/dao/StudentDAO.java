package com.java.course.schoolspring.dao;

import com.java.course.schoolspring.model.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface StudentDAO extends CrudDAO<Student, Integer> {

    Student create(Student student);

    List<Student> findByCourse(String courseName);

    void deleteById(Integer id);

    Optional<Student> findById(Integer id);

    Student update(Student student) throws SQLException;

    List<Student> findAll();

}
