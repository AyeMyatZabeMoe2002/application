package com.example.application;
public class Qualification {

    private int _id;
    private String title;
    private String year;
    private int user_id;


    public Qualification(String title, String year, int user_id) {
        this.title = title;
        this.year  = year;
        this.user_id = user_id;
    }


    public Qualification(int _id, String title, String year) {
        this._id = _id;
        this.title = title;
        this.year = year;
        this.user_id = user_id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
