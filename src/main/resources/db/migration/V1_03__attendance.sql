BEGIN;
CREATE TABLE IF NOT EXISTS "attendance" (
    "lesson_id" serial references "lessons" ("lesson_id"),
    "student_id" serial references "students" ("student_id")
);
COMMIT;