INSERT INTO school_console_app.courses (course_id, course_name, course_description)
VALUES (10001, 'Math', 'Mathematics course'),
       (10002, 'Biology', 'Biology course'),
       (10003, 'Physics', 'Physics course'),
       (10004, 'Chemistry', 'Chemistry course'),
       (10005, 'Computer Science', 'Computer Science course'),
       (10006, 'Literature', 'Literature course'),
       (10007, 'Art', 'Art course'),
       (10008, 'Music', 'Music course'),
       (10009, 'Economics', 'Economics course'),
       (10010, 'History', 'History course');


INSERT INTO school_console_app.groups (group_id, group_name)
VALUES (10001, 'A-01'),
       (10002, 'B-02'),
       (10003, 'C-03'),
       (10004, 'D-04'),
       (10005, 'E-05'),
       (10006, 'F-06'),
       (10007, 'G-07'),
       (10008, 'H-08'),
       (10009, 'I-09'),
       (10010, 'J-10');


INSERT INTO school_console_app.students (student_id, group_id, first_name, last_name)
VALUES (10001, 10001, 'Mia', 'Miller'),
       (10002, 10002, 'Mia', 'Miller'),
       (10003, 10003, 'Mila', 'Miller'),
       (10004, 10004, 'Mila', 'Miller'),
       (10005, 10005, 'Mila', 'Miller'),
       (10006, 10006, 'Mila', 'Miller'),
       (10007, 10007, 'Mila', 'Miller'),
       (10008, 10008, 'Mila', 'Miller'),
       (10009, 10009, 'Mila', 'Miller'),
       (10010, 10010, 'Mila', 'Miller');

insert INTO school_console_app.students_courses (student_id, course_id)
values (10001, 10001),
       (10002, 10001),
       (10002, 10002),
       (10003, 10001),
       (10003, 10002),
       (10003, 10003),
       (10004, 10001),
       (10004, 10002),
       (10004, 10003),
       (10005, 10001),
       (10005, 10002),
       (10005, 10003),
       (10005, 10004),
       (10006, 10001),
       (10007, 10002),
       (10008, 10003),
       (10009, 10004),
       (10010, 10005);
