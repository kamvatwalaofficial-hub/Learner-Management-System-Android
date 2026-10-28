package com.example.learnermanagementsystem;

public class Module {
    private int moduleID;
    private String moduleName;
    private String duration;

    public Module(int moduleID, String moduleName, String duration) {
        this.moduleID = moduleID;
        this.moduleName = moduleName;
        this.duration = duration;
    }

    public int getModuleID() {
        return moduleID;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getDuration() {
        return duration;
    }
}
