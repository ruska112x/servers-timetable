BEGIN;
-- groups
INSERT INTO "groups" ("group_name")
VALUES ('MMB-104'),
    ('MMB-103');
-- students
INSERT INTO "students" (
        "student_surname",
        "student_name",
        "student_status",
        "group_id"
    )
VALUES ('Mikula', 'Gerhard', 'study', 1),
    ('Shalashov', 'Maxim', 'study', 1),
    ('Shelyagin', 'Andrew', 'study', 2),
    ('Izmailov', 'Matew', 'study', 2);
-- subjects
INSERT INTO "subjects" ("subject_name")
VALUES ('Calculus'),
    ('Algebra and Geometry');
-- teachers
INSERT INTO "teachers" (
        "teacher_surname",
        "teacher_name",
        "teacher_patronymic",
        "teacher_position"
    )
VALUES (
        'Ashaev',
        'Igor',
        'Victorovich',
        'Associate Professor'
    ),
    (
        'Ashaeva',
        'Julia',
        'Mikhailovna',
        'Associate Professor'
    );
-- lessons
INSERT INTO "lessons" (
        "lesson_date",
        "lesson_number_in_schedule",
        "subject_id",
        "teacher_id"
    )
VALUES (TO_DATE('23042024', 'DDMMYYYY'), 3, 1, 1),
    (TO_DATE('23042024', 'DDMMYYYY'), 3, 1, 2);
-- lessons for groups
INSERT INTO "lessons_for_groups" ("lesson_id", "group_id")
VALUES (1, 2),
    (2, 1);
INSERT INTO "attendance" ("lesson_id", "student_id")
VALUES (1, 1),
    (1, 2);
COMMIT;