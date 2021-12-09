package com.hp.school.dao;

import com.hp.school.pojo.Student;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface StudentDao {
    //从“student.txt”文档中读出学生信息，并添加到List集合中
    List<Student> studentTxtToList();

    //添加学生信息，并返回添加成功的记录总数
    int addStudent(List<Student> list);

    //根据学号查询学生，返回学生对象
    Student getStudentBySno(int studentNo);

    //根据生日区间查询学生信息，返回集合
    List<Student> getStudentsByDate(Date startDate, Date endDate);

    //根据年级编号统计该年级的男女数量各是多少
    Map<String,Integer> countSexByGradeId(int gradeId);

    //删除4年级"储备年级"，但是4年级有一个学生，将这个学生转移到2年级
    boolean removeGradeById();

    //查询年龄大于age的学生姓名、年级名称，将结果储存到Map集合中
    Map<String,String> getSNameAndGName(int age );
}
