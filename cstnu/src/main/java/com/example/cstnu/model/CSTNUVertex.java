package com.example.cstnu.model;

public class CSTNUVertex {

    private String Type;
    private String id;
    private String Obs;
    private String Label;
    private int x;
    private int y;
    private int c;


    public CSTNUVertex(String id , int x, int y) {
        this(id, null, null, x, y , 1);
    }

    public CSTNUVertex(String id, String Obs, String Label, int x , int y, int c) {
        this.id = id;
        this.Obs = Obs;
        this.Label = Label;
        this.x = x;
        this.y = y;
        this.c = c;
    }



    @Override
    public int hashCode()
    {
        return (id == null) ? 0 : id.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CSTNUVertex other = (CSTNUVertex) obj;
        if (id == null) {
            return other.id == null;
        } else {
            return id.equals(other.id);
        }
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
        sb.append(")");
        return sb.toString();
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
}
