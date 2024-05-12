package com.java.course.schoolspring.service.impl;

import com.java.course.schoolspring.dao.StudentDAO;
import com.java.course.schoolspring.model.Student;
import com.java.course.schoolspring.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDAO studentDAO;

    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public void addNewStudentService(Student student) {
        studentDAO.save(student);
    }
    @Override
    public void deleteStudentByIdService(Integer studentId) {
        studentDAO.deleteById(studentId);
    }
}
