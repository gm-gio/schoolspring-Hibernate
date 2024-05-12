package com.java.course.schoolspring.service;

import com.java.course.schoolspring.model.Group;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    public List<Group> findGroupsWithLessOrEqualStudentCount(int maxStudentCount);

    Optional<Group> findGroupById(int groupId);
}
