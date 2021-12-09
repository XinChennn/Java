package com.hp.school.pojo;

import java.util.Date;

/**
 * 学生类实体
 */
public class Student {
   private int  studentNo;    // 学号
    private String studentName;  // 姓名
    private String sex;          //性别
    private int gradeId;      //年级编号
    private Date borndate;     //出生日期

    public int getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public Date getBorndate() {
        return borndate;
    }

    public void setBorndate(Date borndate) {
        this.borndate = borndate;
    }

    public Student(int studentNo, String studentName, String sex, int gradeId, Date borndate) {
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.sex = sex;
        this.gradeId = gradeId;
        this.borndate = borndate;
    }


    @Override
    public String toString() {
        return "Student{" +
                "studentNo=" + studentNo +
                ", studentName='" + studentName + '\'' +
                ", sex='" + sex + '\'' +
                ", gradeId=" + gradeId +
                ", borndate=" + borndate +
                '}';
    }
}
