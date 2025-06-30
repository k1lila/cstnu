package com.example.cstnu.dto;

public class TaskDTO {

    private String id;
    private Integer min;
    private Integer max;
    private Integer c;
    private String prepLabel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public String getPrepLabel() {
        return prepLabel;
    }

    public void setPrepLabel(String prepLabel) {
        this.prepLabel = prepLabel;
    }
}
