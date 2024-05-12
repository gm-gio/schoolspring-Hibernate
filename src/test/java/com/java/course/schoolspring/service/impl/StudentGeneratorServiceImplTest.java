package com.java.course.schoolspring.service.impl;

import com.java.course.schoolspring.dao.GroupDAO;
import com.java.course.schoolspring.dao.StudentDAO;
import com.java.course.schoolspring.model.Student;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {StudentGeneratorServiceImpl.class})
public class StudentGeneratorServiceImplTest {

    @MockBean
    StudentDAO studentDAO;
    @MockBean
    GroupDAO groupDAO;

    @Autowired
    StudentGeneratorServiceImpl studentGeneratorService;

    @Captor
    ArgumentCaptor<List<Student>> studentCaptor;


    @Test
    void generatedStudentsWhenNeed() {
        when(studentDAO.count()).thenReturn(0);
        when(groupDAO.count()).thenReturn(0);

        studentGeneratorService.generateStudentsIfNeed();

        verify(studentDAO, times(1)).saveAll(studentCaptor.capture());

        List<Student> capturedStudents = studentCaptor.getValue();

        assertEquals(200, capturedStudents.size());
    }


}
