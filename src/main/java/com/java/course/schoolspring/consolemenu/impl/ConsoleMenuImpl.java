package com.java.course.schoolspring.consolemenu.impl;

import com.java.course.schoolspring.consolemenu.Console;
import com.java.course.schoolspring.consolemenu.ConsoleMenu;
import com.java.course.schoolspring.model.Group;
import com.java.course.schoolspring.model.Student;
import com.java.course.schoolspring.service.CourseService;
import com.java.course.schoolspring.service.GroupService;
import com.java.course.schoolspring.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.java.course.schoolspring.ConsoleMenuRunner.logger;


@Component
public class ConsoleMenuImpl implements ConsoleMenu {

    private final Console console;

    private final CourseService courseService;
    private final GroupService groupService;
    private final StudentService studentService;

    public ConsoleMenuImpl(Console console, CourseService courseService, GroupService groupService, StudentService studentService) {
        this.console = console;
        this.courseService = courseService;
        this.groupService = groupService;
        this.studentService = studentService;
    }

    @Override
    @Transactional
    public void showMainMenu() {
        boolean done = false;
        while (!done) {
            showMenu();

            int choice = console.nextInt();

            switch (choice) {
                case 1:
                    searchGroupsByStudentCount();
                    break;
                case 2:
                    searchStudentByCourseName();
                    break;

                case 3:
                    addStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    addStudentToCourse();
                    break;
                case 6:
                    removeStudentFromCourse();
                    break;
                case 0:
                    done = true;
                    break;
                default:
                    logger.error("Invalid choice entered: {}", choice);
                    console.println("Invalid choice. Please enter a valid action number.");
            }
        }
    }
    @Transactional
    private void showMenu() {
        console.println("=== Main Menu ===");
        console.println("1. Find all groups with less or equal students’ number");
        console.println("2. Find all students related to the course with the given name");
        console.println("3. Add a new student");
        console.println("4. Delete student");
        console.println("5. Add Student to Course");
        console.println("6. Remove Student from Course");
        console.println("0. Exit");
    }
    @Transactional
    public void searchStudentByCourseName() {
        console.println("Enter the course name: ");
        String courseName = console.nextLine();

        List<Student> students = courseService.findStudentByCourseName(courseName);
        if (students.isEmpty()) {
            console.println("No students found for the course: " + courseName);
        } else {
            console.println("Students related to the course " + courseName + ":");
        }
        for (Student student : students) {
            console.println(String.valueOf(student));
        }
    }

    @Transactional
    public void searchGroupsByStudentCount() {
        console.println("Enter the maximum number of students: ");
        int maxStudentCount = console.nextInt();

        List<Group> groups = groupService.findGroupsWithLessOrEqualStudentCount(maxStudentCount);

        if (groups.isEmpty()) {
            console.println("No groups found with less or equal students' number.");
        } else {
            console.println("Groups with less or equal students' number:");
            for (Group group : groups) {
                console.println(String.valueOf(group));
            }
        }
    }
    @Transactional
    public void addStudent() {
        console.println("Enter the first name of the new student: ");
        String firstName = console.nextLine();
        console.println("Enter the last name of the new student: ");
        String lastName = console.nextLine();
        console.println("Enter the group ID for the new student: ");
        int groupId = Integer.parseInt(console.nextLine());


        Group group = groupService.findGroupById(groupId).orElseThrow(() -> new RuntimeException("Group with id=%d not found".formatted(groupId)));


        Student newStudent = new Student();
        newStudent.setFirstName(firstName);
        newStudent.setLastName(lastName);
        newStudent.setGroup(group);

        studentService.addNewStudentService(newStudent);

        console.println("New student added successfully.");
    }

    @Transactional
    public void deleteStudent() {
        console.println("Enter the student ID to delete: ");
        int studentId = Integer.parseInt(console.nextLine());

        studentService.deleteStudentByIdService(studentId);
        console.println("Student with ID " + studentId + " deleted successfully.");
    }
    @Transactional
    public void addStudentToCourse() {
        console.println("Enter the student ID to add to the course: ");
        Integer studentId = Integer.parseInt(console.nextLine());

        console.println("Enter the course ID to add the student: ");
        Integer courseId = Integer.parseInt(console.nextLine());

        courseService.addStudentToCourse(studentId, courseId);
        console.println("Student with ID " + studentId + " added to the course with ID " + courseId + " successfully.");
    }
    @Transactional
    public void removeStudentFromCourse() {
        console.println("Enter the student ID to remove from the course: ");
        Integer studentId = Integer.parseInt(console.nextLine());

        console.println("Enter the course ID to remove the student from: ");
        Integer courseId = Integer.parseInt(console.nextLine());

        courseService.deleteStudentFromCourseService(studentId, courseId);

        console.println("Student with ID " + studentId + " removed from the course with ID " + courseId + " successfully.");
    }
}