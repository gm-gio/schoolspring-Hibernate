package com.java.course.schoolspring.service.impl;

import com.java.course.schoolspring.dao.GroupDAO;
import com.java.course.schoolspring.model.Group;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GroupGeneratorServiceImpl implements com.java.course.schoolspring.service.GroupGeneratorService {


    private final GroupDAO groupDAO;

    public GroupGeneratorServiceImpl(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    private final String[] groupNames = {"A-01", "B-02", "C-03", "D-04", "E-05", "F-06", "G-07", "H-08", "I-09", "J-10"};

    @Override
    public void generateGroupsIfNeed() {
        if (groupDAO.count() == 0) {
            List<Group> groups = new ArrayList<>();
            for (String groupName : groupNames) {
                Group group = new Group(groupName);
                groups.add(group);
            }
            groupDAO.saveAll(groups);
        }
    }
}
