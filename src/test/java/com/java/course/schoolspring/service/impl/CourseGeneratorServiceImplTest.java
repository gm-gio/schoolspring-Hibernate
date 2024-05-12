package com.java.course.schoolspring.service.impl;

import com.java.course.schoolspring.dao.CourseDAO;
import com.java.course.schoolspring.model.Course;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {CourseGeneratorServiceImpl.class})
public class CourseGeneratorServiceImplTest {


    @MockBean
    CourseDAO courseDao;

    @Autowired
    CourseGeneratorServiceImpl courseGeneratorService;

    @Captor
    ArgumentCaptor<List<Course>> courseCaptor;

    @Test
    void generateCoursesIfNeed() {
        when(courseDao.count()).thenReturn(0);
        courseGeneratorService.generateCoursesIfNeed();
        verify(courseDao, times(1)).saveAll(courseCaptor.capture());
        List<Course> capturedCourses = courseCaptor.getValue();
        assertEquals(10, capturedCourses.size());
    }
}
