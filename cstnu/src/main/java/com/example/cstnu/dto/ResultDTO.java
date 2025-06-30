package com.example.cstnu.dto;

public class ResultDTO {

    private String status;
    private String negativeLoopId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNegativeLoopId() {
        return negativeLoopId;
    }

    public void setNegativeLoopId(String negativeLoopId) {
        this.negativeLoopId = negativeLoopId;
    }
}
