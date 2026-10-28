package com.example.learnermanagementsystem;

public class Instructor {
    private int instructorID;
    private String instructorName;
    private String instructorSurname;

    public Instructor(int instructorID, String instructorName, String instructorSurname) {
        this.instructorID = instructorID;
        this.instructorName = instructorName;
        this.instructorSurname = instructorSurname;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getInstructorSurname() {
        return instructorSurname;
    }
}
