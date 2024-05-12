package com.java.course.schoolspring.service;

import com.java.course.schoolspring.model.Student;

public interface StudentService {

    void addNewStudentService(Student student);

    void deleteStudentByIdService(Integer studentId);
}
