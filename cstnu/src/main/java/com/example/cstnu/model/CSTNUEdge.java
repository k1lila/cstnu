package com.example.cstnu.model;

import org.springframework.stereotype.Component;


public class CSTNUEdge {

    private String id;
    private String Source;
    private String Target;
    private int Value;
    private Type Type;
    private String LabeledValues;

    public CSTNUEdge(String id, String Source, String Target , int Value, Type Type, String LabeledValues){
        this.id = id;
        this.Source = Source;
        this.Target = Target;
        this.Value = Value;
        this.Type = Type;
        this.LabeledValues = LabeledValues;
    }

    public String getLabeledValues() {
        return LabeledValues;
    }

    public void setLabeledValues(String labeledValues) {
        LabeledValues = labeledValues;
    }

    @Override
    public int hashCode()
    {
        return (id == null) ? 0 : id.hashCode();
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getTarget() {
        return Target;
    }

    public void setTarget(String target) {
        Target = target;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "CSTNUEdge{" +
                "id='" + id + '\'' +
                ", Source='" + Source + '\'' +
                ", Target='" + Target + '\'' +
                ", Value=" + Value +
                ", Type=" + Type +
                ", LabeledValues='" + LabeledValues + '\'' +
                '}';
    }
}
