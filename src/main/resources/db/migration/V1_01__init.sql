BEGIN;
CREATE TABLE IF NOT EXISTS "groups" (
    "group_id" serial NOT NULL,
    "group_name" varchar(16) NOT NULL UNIQUE,
    PRIMARY KEY("group_id")
);
CREATE TABLE IF NOT EXISTS "students" (
    "student_id" serial NOT NULL,
    "student_surname" varchar(64) NOT NULL,
    "student_name" varchar(32) NOT NULL,
    "student_patronymic" varchar(32),
    "student_status" varchar(32) NOT NULL,
    PRIMARY KEY("student_id")
);
CREATE TABLE IF NOT EXISTS "subjects" (
    "subject_id" serial NOT NULL,
    "subject_name" varchar(32) NOT NULL UNIQUE,
    PRIMARY KEY("subject_id")
);
CREATE TABLE IF NOT EXISTS "teachers" (
    "teacher_id" serial NOT NULL,
    "teacher_surname" varchar(32) NOT NULL,
    "teacher_name" varchar(32) NOT NULL,
    "teacher_patronymic" varchar(32),
    "teacher_position" varchar(32) NOT NULL,
    PRIMARY KEY("teacher_id")
);
CREATE TABLE IF NOT EXISTS "lessons" (
    "lesson_id" serial NOT NULL,
    "lesson_date" date NOT NULL,
    "lesson_number_in_schedule" int NOT NULL,
    PRIMARY KEY("lesson_id")
);
CREATE TABLE IF NOT EXISTS "lessons_for_groups"(
    "lesson_id" serial references "lessons" ("lesson_id"),
    "group_id" serial references "groups" ("group_id")
);
ALTER TABLE "students"
ADD COLUMN "group_id" serial references "groups" ("group_id");
ALTER TABLE "lessons"
ADD COLUMN "subject_id" serial references "subjects" ("subject_id");
ALTER TABLE "lessons"
ADD COLUMN "teacher_id" serial references "teachers" ("teacher_id");
COMMIT;