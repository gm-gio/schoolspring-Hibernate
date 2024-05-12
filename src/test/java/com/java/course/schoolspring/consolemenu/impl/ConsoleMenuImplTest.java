package com.java.course.schoolspring.consolemenu.impl;

import com.java.course.schoolspring.consolemenu.Console;
import com.java.course.schoolspring.model.Group;
import com.java.course.schoolspring.model.Student;
import com.java.course.schoolspring.service.CourseService;
import com.java.course.schoolspring.service.GroupService;
import com.java.course.schoolspring.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {ConsoleMenuImpl.class})
@ActiveProfiles("test")
public class ConsoleMenuImplTest {
    @Autowired
    private ConsoleMenuImpl consoleMenu;

    @MockBean
    private Console console;
    @MockBean
    CourseService courseService;
    @MockBean
    GroupService groupService;
    @MockBean
    StudentService studentService;

    @Captor
    ArgumentCaptor<String> stringCaptor;


    @Test
    void shouldSearchGroupsByStudentCount() {
        when(groupService.findGroupsWithLessOrEqualStudentCount(2))
                .thenReturn(List.of(
                        new Group(1, "Test"),
                        new Group(2, "Test2")
                ));
        when(console.nextLine()).thenReturn("");
        when(console.nextInt()).thenReturn(1, 2, 0);
        consoleMenu.showMainMenu();
        verify(console, atLeast(1)).println(stringCaptor.capture());
        String actual = stringCaptor.getAllValues()
                .stream().collect(Collectors.joining("\n"));

        String expected = """
                === Main Menu ===
                1. Find all groups with less or equal students’ number
                2. Find all students related to the course with the given name
                3. Add a new student
                4. Delete student
                5. Add Student to Course
                6. Remove Student from Course
                0. Exit
                Enter the maximum number of students:\s
                Groups with less or equal students' number:
                Group{groupId=1, groupName='Test'}
                Group{groupId=2, groupName='Test2'}
                === Main Menu ===
                1. Find all groups with less or equal students’ number
                2. Find all students related to the course with the given name
                3. Add a new student
                4. Delete student
                5. Add Student to Course
                6. Remove Student from Course
                0. Exit""";

        assertEquals(expected, actual);
    }

    @Test
    void shouldSearchStudentByCourseName() {
        Group group1 = new Group(1, "one");
        Group group2 = new Group(2, "one");
        when(courseService.findStudentByCourseName("Math")).thenReturn(List.of(
                new Student(1, "FirstName1", "LastName1", group1),
                new Student(2, "FirstName2", "LastName2", group2)
        ));
        when(console.nextLine()).thenReturn("Math");

        consoleMenu.searchStudentByCourseName();

        verify(console, atLeastOnce()).println(stringCaptor.capture());
        String actual = stringCaptor.getAllValues().stream().collect(Collectors.joining("\n"));

        String expected = """ 
                Enter the course name:\s
                Students related to the course Math:
                Student{studentId=1, groupId=1, firstName='FirstName1', lastName='LastName1'}
                === Main Menu ===
                1. Find all groups with less or equal students’ number
                2. Find all students related to the course with the given name
                3. Add a new student
                4. Delete student
                5. Add Student to Course
                6. Remove Student from Course
                0. Exit
                Student{studentId=2, groupId=2, firstName='FirstName2', lastName='LastName2'}
                === Main Menu ===
                1. Find all groups with less or equal students’ number
                2. Find all students related to the course with the given name
                3. Add a new student
                4. Delete student
                5. Add Student to Course
                6. Remove Student from Course
                0. Exit""";

        assertEquals(expected, actual);
    }

    @Test
    void shouldAddNewStudent() {
        when(console.nextLine())
                .thenReturn("Name")
                .thenReturn("LastName")
                .thenReturn("1");
        consoleMenu.addStudent();

        verify(console, times(12)).println(anyString());
        verify(console).println("Enter the first name of the new student: ");
        verify(console).println("Enter the last name of the new student: ");
        verify(console).println("Enter the group ID for the new student: ");
        verify(console).println("New student added successfully.");

        ArgumentCaptor<Student> studentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentService).addNewStudentService(studentCaptor.capture());
        Student capturedStudent = studentCaptor.getValue();
        assertEquals("Name", capturedStudent.getFirstName());
        assertEquals("LastName", capturedStudent.getLastName());
        assertEquals(1, capturedStudent.getGroup().getId());
    }

    @Test
    void shouldDeleteStudent() {
        int studentId = 1;
        when(console.nextLine()).thenReturn(String.valueOf(studentId));
        consoleMenu.deleteStudent();

        verify(console).println("Enter the student ID to delete: ");
        verify(console).println("Student with ID " + studentId + " deleted successfully.");
        ArgumentCaptor<Integer> studentIdCaptor = ArgumentCaptor.forClass(Integer.class);

        verify(studentService).deleteStudentByIdService(studentIdCaptor.capture());
        assertEquals(studentId, studentIdCaptor.getValue());
    }

    @Test
    void shouldAddStudentToCourse() {
        int studentId = 1;
        int courseId = 1;
        when(console.nextLine()).thenReturn(String.valueOf(studentId), String.valueOf(courseId));
        consoleMenu.addStudentToCourse();

        verify(console).println("Enter the student ID to add to the course: ");
        verify(console).println("Enter the course ID to add the student: ");
        verify(console).println("Student with ID " + studentId + " added to the course with ID " + courseId + " successfully.");

        ArgumentCaptor<Integer> studentIdCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> courseIdCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(courseService).addStudentToCourse(studentIdCaptor.capture(), courseIdCaptor.capture());

        assertEquals(studentId, studentIdCaptor.getValue());
        assertEquals(courseId, courseIdCaptor.getValue());
    }

    @Test
    void shouldDeleteStudentFromCourse() {
        int studentId = 1;
        int courseId = 1;
        when(console.nextLine()).thenReturn(String.valueOf(studentId), String.valueOf(courseId));
        consoleMenu.removeStudentFromCourse();

        verify(console).println("Enter the student ID to remove from the course: ");
        verify(console).println("Enter the course ID to remove the student from: ");
        verify(console).println("Student with ID " + studentId + " removed from the course with ID " + courseId + " successfully.");

        ArgumentCaptor<Integer> studentIdCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> courseIdCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(courseService).deleteStudentFromCourseService(studentIdCaptor.capture(), courseIdCaptor.capture());

        assertEquals(studentId, studentIdCaptor.getValue());
        assertEquals(courseId, courseIdCaptor.getValue());
    }
}
