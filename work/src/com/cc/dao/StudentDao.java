package com.cc.dao;

import com.cc.pojo.Student;

import java.util.List;

public interface StudentDao {
    List<Student> getAll();
    int insert(List<Student> list);
}
