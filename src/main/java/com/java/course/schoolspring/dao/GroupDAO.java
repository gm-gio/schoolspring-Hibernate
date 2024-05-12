package com.java.course.schoolspring.dao;

import com.java.course.schoolspring.model.Group;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GroupDAO extends CrudDAO<Group, Integer> {

    List<Group> findGroupsByStudentCount(int maxStudentCount);

    Group create(Group group);

    Optional<Group> findById(Integer id);

    List<Group> findAll();

    Group update(Group group) throws SQLException;

    void deleteById(Integer id);

}
