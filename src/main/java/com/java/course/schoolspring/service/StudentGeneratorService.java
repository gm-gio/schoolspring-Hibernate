package com.java.course.schoolspring.service;

import java.sql.SQLException;

public interface StudentGeneratorService {
    void generateStudentsIfNeed();

    void assignStudentsToGroups() throws SQLException;
}
