package com.example.learnermanagementsystem;

public class Student {
    private int studentID;
    private String studentName;
    private String studentSurname;
    private String dob;

    public Student(int studentID, String studentName, String studentSurname, String dob) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.dob = dob;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public String getDob() {
        return dob;
    }
}
