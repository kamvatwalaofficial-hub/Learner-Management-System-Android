package com.example.learnermanagementsystem;

public class Task {
    private int taskID;
    private String taskName;
    private String dueDate;
    private int moduleID;

    public Task(int taskID, String taskName, String dueDate, int moduleID) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.moduleID = moduleID;
    }

    public int getTaskID() {
        return taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public int getModuleID() {
        return moduleID;
    }
}
