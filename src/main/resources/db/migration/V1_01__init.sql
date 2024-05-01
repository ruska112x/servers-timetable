BEGIN;
CREATE TABLE IF NOT EXISTS "groups" (
    "group_id" SERIAL PRIMARY KEY,
    "group_name" varchar(16) NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS "students" (
    "student_id" SERIAL PRIMARY KEY,
    "student_surname" varchar(32) NOT NULL,
    "student_name" varchar(32) NOT NULL,
    "student_patronymic" varchar(32),
    "student_status" varchar(32) NOT NULL,
    "group_id" INT,
    FOREIGN KEY ("group_id") REFERENCES "groups" ("group_id") ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS "subjects" (
    "subject_id" SERIAL PRIMARY KEY,
    "subject_name" varchar(32) NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS "teachers" (
    "teacher_id" SERIAL PRIMARY KEY,
    "teacher_surname" varchar(32) NOT NULL,
    "teacher_name" varchar(32) NOT NULL,
    "teacher_patronymic" varchar(32),
    "teacher_position" varchar(32) NOT NULL
);
CREATE TABLE IF NOT EXISTS "lessons" (
    "lesson_id" SERIAL PRIMARY KEY,
    "lesson_date" date NOT NULL,
    "lesson_number_in_schedule" int NOT NULL,
    "subject_id" INT,
    "teacher_id" INT,
    FOREIGN KEY ("subject_id") REFERENCES "subjects" ("subject_id") ON DELETE CASCADE,
    FOREIGN KEY ("teacher_id") REFERENCES "teachers" ("teacher_id") ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS "lessons_for_groups"(
    "lesson_id" INT,
    "group_id" INT,
    PRIMARY KEY ("lesson_id", "group_id"),
    FOREIGN KEY ("lesson_id") REFERENCES "lessons" ("lesson_id") ON DELETE CASCADE,
    FOREIGN KEY ("group_id") REFERENCES "groups" ("group_id") ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS "attendance" (
    "lesson_id" INT,
    "student_id" INT,
    PRIMARY KEY ("lesson_id", "student_id"),
    FOREIGN KEY ("lesson_id") REFERENCES "lessons" ("lesson_id") ON DELETE CASCADE,
    FOREIGN KEY ("student_id") REFERENCES "students" ("student_id") ON DELETE CASCADE
);
COMMIT;