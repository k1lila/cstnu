package com.example.cstnu.dto;

public class CSTNUEdgeDTO {

    private String id;
    private String source;
    private String target;
    private Integer value;
    private String type;

    public String getLabelledValues() {
        return labelledValues;
    }

    public void setLabelledValues(String labelledValues) {
        this.labelledValues = labelledValues;
    }

    private String labelledValues;

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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CSTNUEdgeDTO{" +
                "id='" + id + '\'' +
                ", source='" + source + '\'' +
                ", target='" + target + '\'' +
                ", value=" + value +
                ", type='" + type + '\'' +
                '}';
    }
}
