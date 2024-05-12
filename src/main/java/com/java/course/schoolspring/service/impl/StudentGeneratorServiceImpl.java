package com.java.course.schoolspring.service.impl;

import com.java.course.schoolspring.dao.GroupDAO;
import com.java.course.schoolspring.dao.StudentDAO;
import com.java.course.schoolspring.model.Group;
import com.java.course.schoolspring.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentGeneratorServiceImpl implements com.java.course.schoolspring.service.StudentGeneratorService {

    private StudentDAO studentDAO;
    private GroupDAO groupDAO;

    public StudentGeneratorServiceImpl(StudentDAO studentDAO, GroupDAO groupDAO) {
        this.studentDAO = studentDAO;
        this.groupDAO = groupDAO;
    }

    private final String[] firstNames = {"John", "Jane", "Michael", "Emily", "David", "Emma", "Christopher", "Olivia", "Andrew",
            "Sophia", "Daniel", "Mia", "Ethan", "Isabella", "Matthew", "Ava", "Alexander", "Grace",
            "James", "Lily"};
    private final String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore",
            "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia",
            "Martinez", "Jones"};

    @Override
    public void generateStudentsIfNeed() {

        if (studentDAO.count() == 0) {
            List<Student> students = new ArrayList<>();
            for (int i = 0; i < 200; i++) {
                String firstName = firstNames[(int) (Math.random() * firstNames.length)];
                String lastName = lastNames[(int) (Math.random() * lastNames.length)];

                Student student = new Student(firstName, lastName);
                students.add(student);
            }
            studentDAO.saveAll(students);
        }
    }

    @Override
    public void assignStudentsToGroups() throws SQLException {
        List<Student> students = studentDAO.findAll();
        List<Group> groups = groupDAO.findAll();
        for (Student student : students) {
            Group group = groups.get((int) (Math.random() * groups.size()));
            student.setGroup(group);
        }
    }
}
