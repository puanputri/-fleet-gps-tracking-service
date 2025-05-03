package com.chaaw.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private int status;
    private LocalDateTime timestamp;
    private String path;
    
    public ErrorResponse(String message, int status, String path) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.path = path;
    }
    
    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}