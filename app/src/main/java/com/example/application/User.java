package com.example.application;
import java.io.Serializable;
public class User implements Serializable {

    //declare properties (if you declare properties, should use private)
    private  long   id; //primary key
    private  String name;
    private  String email;
    private  String remark;

    //create constructor (right click--generate--constructor,select all, click)

    public User(long id, String name, String email, String remark) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.remark = remark;
    }

    public User(String name, String email, String remark) {
        this.name = name;
        this.email = email;
        this.remark = remark;
    }//end of constructor

    //create getter and setter (right click--generate--getter and setter--select all)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}//end of class
