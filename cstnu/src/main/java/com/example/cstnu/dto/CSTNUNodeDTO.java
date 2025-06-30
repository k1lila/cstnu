package com.example.cstnu.dto;

public class CSTNUNodeDTO {


    private String id;
    private String Obs;
    private String Label;
    private int x;
    private int y;
    private int c;

    public CSTNUNodeDTO(){
        //empty constructor
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObs() {
        return Obs;
    }

    public void setObs(String obs) {
        Obs = obs;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(id);
        if (Obs != null) {
            sb.append(",").append(Obs);
        }
        if (Label != null) {
            sb.append(",").append(Label);
        }
        sb.append(",").append(x);
        sb.append(",").append(y);
        sb.append(",").append(c);
        sb.append(")");
        return sb.toString();
    }
}
