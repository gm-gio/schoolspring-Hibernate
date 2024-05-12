package com.java.course.schoolspring.service.impl;

import com.java.course.schoolspring.dao.GroupDAO;
import com.java.course.schoolspring.model.Group;
import com.java.course.schoolspring.service.GroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupDAO groupDAO;


    public GroupServiceImpl(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Group> findGroupsWithLessOrEqualStudentCount(int maxStudentCount) {
        return groupDAO.findGroupsByStudentCount(maxStudentCount);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Group> findGroupById(int groupId) {
        return groupDAO.findById(groupId);
    }
}
