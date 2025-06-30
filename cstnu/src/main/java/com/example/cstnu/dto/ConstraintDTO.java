package com.example.cstnu.dto;

public class ConstraintDTO {

    private String id;
    private String source;
    private String target;
    private Integer constraintType;
    private String type;
    private Integer labeledValues;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getConstraintType() {
        return constraintType;
    }

    public void setConstraintType(Integer constraintType) {
        this.constraintType = constraintType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLabeledValues() {
        return labeledValues;
    }

    public void setLabeledValues(Integer labeledValues) {
        this.labeledValues = labeledValues;
    }
}
