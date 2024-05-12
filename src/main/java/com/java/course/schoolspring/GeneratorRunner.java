package com.java.course.schoolspring;


import com.java.course.schoolspring.service.CourseGeneratorService;
import com.java.course.schoolspring.service.GroupGeneratorService;
import com.java.course.schoolspring.service.StudentGeneratorService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class GeneratorRunner implements ApplicationRunner {
    private final CourseGeneratorService courseGeneratorService;
    private final GroupGeneratorService groupGeneratorService;
    private final StudentGeneratorService studentGeneratorService;

    public GeneratorRunner(CourseGeneratorService courseGeneratorService,
                           GroupGeneratorService groupGeneratorService,
                           StudentGeneratorService studentGeneratorService) {
        this.courseGeneratorService = courseGeneratorService;
        this.groupGeneratorService = groupGeneratorService;
        this.studentGeneratorService = studentGeneratorService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        courseGeneratorService.generateCoursesIfNeed();
        groupGeneratorService.generateGroupsIfNeed();
        studentGeneratorService.generateStudentsIfNeed();
        studentGeneratorService.assignStudentsToGroups();
    }
}

