package com.cc.dao.impl;

import com.cc.dao.StudentDao;
import com.cc.pojo.Student;
import com.cc.utils.BaseDaoPack;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    BaseDaoPack baseDaoPack=new BaseDaoPack();
    @Override
    public List<Student> getAll() {
        FileInputStream fil=null;
        SimpleDateFormat sm=new SimpleDateFormat("yyyy/MM/dd");
        List<Student> list=new ArrayList<>();
        Student student=null;
        try {
            //文件路径
            fil=new FileInputStream("D:\\学习\\作业\\msg.txt");
            //  .available() 获取fil的最大字节，一次性读完
            int available = fil.available();
            //长度就是fil的最大长度
            byte[] bytes=new byte[available];
            //读取
            fil.read(bytes);
            //将byte数组里的字节转化为字符串
            String s=new String(bytes);
            //从换行读
            String[] split = s.split("\r\n");
            for (int i = 0; i < split.length; i++) {
                //拆分-
                String[] split1 = split[i].split("-");
                for (int j = 0; j < split1.length; j++) {
                    //封装成对象
                    student=new Student(split1[0],sm.parse(split1[1]),split1[2]);
                }
                //添加到集合
                list.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fil != null) {
                try {
                    fil.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    @Override
    public int insert(List<Student> list) {
        int update=0;
        String sql = "insert into student values(?,?,?)";
        for (int i = 0; i < list.size(); i++) {
            Student student = list.get(i);
            update += baseDaoPack.update(sql, new Object[]{student.getName(), student.getBirthday(), student.getCountry()});
        }
        baseDaoPack.close();
        return update;
    }

    @Test
    public void Test(){
        List<Student> all = getAll();
        int insert = insert(all);
        System.out.println(insert);
    }
}
