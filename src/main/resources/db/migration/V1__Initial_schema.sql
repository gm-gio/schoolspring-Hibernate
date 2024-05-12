CREATE SCHEMA IF NOT EXISTS school_console_app;

CREATE TABLE IF NOT EXISTS school_console_app.courses
(
    course_id          SERIAL PRIMARY KEY,
    course_name        VARCHAR(255) NOT NULL,
    course_description VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS school_console_app.groups
(
    group_id   SERIAL PRIMARY KEY,
    group_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS school_console_app.students
(
    student_id SERIAL PRIMARY KEY,
    group_id   INT,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    FOREIGN KEY (group_id) REFERENCES school_console_app.groups (group_id) on delete cascade
);

CREATE TABLE IF NOT EXISTS school_console_app.students_courses
(
    student_id INT,
    course_id  INT,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES school_console_app.students (student_id) on DELETE cascade,
    FOREIGN KEY (course_id) REFERENCES school_console_app.courses (course_id) on delete cascade
);

