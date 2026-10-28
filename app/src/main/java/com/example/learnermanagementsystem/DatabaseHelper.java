package com.example.learnermanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "LMS.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Users table for login (admin, instructor, student)
        String createUsersTable = "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT UNIQUE, " +
                "password TEXT, " +
                "role TEXT)";
        db.execSQL(createUsersTable);

        // Students table
        String createStudentsTable = "CREATE TABLE students (" +
                "studentID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "studentName TEXT, " +
                "studentSurname TEXT, " +
                "dob TEXT)";
        db.execSQL(createStudentsTable);

        // Instructors table
        String createInstructorsTable = "CREATE TABLE instructors (" +
                "instructorID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "instructorName TEXT, " +
                "instructorSurname TEXT)";
        db.execSQL(createInstructorsTable);

        // Modules table
        String createModulesTable = "CREATE TABLE modules (" +
                "moduleID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "moduleName TEXT, " +
                "duration TEXT)";
        db.execSQL(createModulesTable);

        // Tasks table
        String createTasksTable = "CREATE TABLE tasks (" +
                "taskID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "taskName TEXT, " +
                "dueDate TEXT, " +
                "moduleID INTEGER, " +
                "FOREIGN KEY(moduleID) REFERENCES modules(moduleID))";
        db.execSQL(createTasksTable);

        // Student Task Status table (for marking tasks complete)
        String createStudentTasksTable = "CREATE TABLE student_tasks (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "studentID INTEGER, " +
                "taskID INTEGER, " +
                "isCompleted INTEGER DEFAULT 0, " +
                "FOREIGN KEY(studentID) REFERENCES students(studentID), " +
                "FOREIGN KEY(taskID) REFERENCES tasks(taskID))";
        db.execSQL(createStudentTasksTable);

        // Insert default admin user
        db.execSQL("INSERT INTO users (username, password, role) VALUES ('admin', 'admin123', 'admin')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop all tables if upgrading
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS students");
        db.execSQL("DROP TABLE IF EXISTS instructors");
        db.execSQL("DROP TABLE IF EXISTS modules");
        db.execSQL("DROP TABLE IF EXISTS tasks");
        db.execSQL("DROP TABLE IF EXISTS student_tasks");
        onCreate(db);
    }
}
