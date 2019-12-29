package com.example.habit;


public class Habit {

    private int h_Id;
    private String h_thing;
    private String h_color;
    private int h_time;
    private String h_text;
    private int h_OK;

    public int getH_Id() {
        return h_Id;
    }

    public void setH_Id(int h_Id) {
        this.h_Id = h_Id;
    }

    public String getH_thing() {
        return h_thing;
    }

    public void setH_thing(String h_thing) {
        this.h_thing = h_thing;
    }

    public String getH_color() {
        return h_color;
    }

    public void setH_color(String h_color) {
        this.h_color = h_color;
    }

    public int getH_time() {
        return h_time;
    }

    public void setH_time(int h_time) {
        this.h_time = h_time;
    }

    public String getH_text() {
        return h_text;
    }

    public void setH_text(String h_text) {
        this.h_text = h_text;
    }

    public int getH_OK() {
        return h_OK;
    }

    public void setH_OK(int h_OK) {
        this.h_OK = h_OK;
    }

    public String toString(){
        return  this.h_Id+","+this.h_thing+","+ this.h_color+","+ this.h_time+","+ this.h_text+","+this.h_OK;
    }

}
