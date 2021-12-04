package com.cc.pojo;

import java.util.Date;

public class Student {
    private String name;
    private Date birthday;
    private String country;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", country='" + country + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Student(String name, Date birthday, String country) {
        this.name = name;
        this.birthday = birthday;
        this.country = country;
    }

    public Student() {
    }
}
