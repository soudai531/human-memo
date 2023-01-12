package com.example.humanmemo.service;

public class ComplaintEntityNotFoundException extends RuntimeException{

    private long taskId;

    public ComplaintEntityNotFoundException(long taskId) {
        super("TaskEntity (id = "+ taskId + ") is not found.");
        this.taskId = taskId;
    }
}
