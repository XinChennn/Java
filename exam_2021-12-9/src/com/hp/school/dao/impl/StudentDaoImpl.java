package com.hp.school.dao.impl;

import com.hp.school.dao.StudentDao;
import com.hp.school.pojo.Student;
import com.hp.school.utils.JDBCUtil;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentDaoImpl implements StudentDao {
    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * @return list
     *题目(一)需求：（15分）
     *    一：补充以下方法缺失代码
     *               （友情提示：StudentList.txt文档中每个字段之间都有一个缩进(Teb)
     *    二：
     *      完成“一”操作之后，在com.hp.school.test.ExamTest测试类的test_studentTxtToList()方法里"*按要求*"完成测试，遍历list集合
     */
    @Override
    public List<Student> studentTxtToList() {
		String path="D:\\StudentList.txt";//文件路径
        Student student=null;   //用于保存Student对象的引用
        String txtString=null;  //保存读取文档内容的字符串
        List<Student> list=new ArrayList<>();   //储存Student对象的集合
        FileInputStream fil=null;   //读取文档的流
        try {
          /* 代码补充：将“D:\\StudentList.txt”文档中的内容读出来，形成一个字符串，保存在String txtString中 */
            fil = new FileInputStream( new File(path));
            int available = fil.available();
            byte[] bytes=new byte[available];
            fil.read(bytes);
            txtString= new String(bytes);
            String[] lineStr = txtString.split( "\r\n" );//分割整个字符串
         /*代码补充：从lineStr数组元素每个中解析出学号、姓名、性别、年级编号、生日信息，封装成Student对象(请过滤掉表头)，并添加到集合，返回List集合*/
            for (int i = 1; i < lineStr.length; i++) {
                String[] split = lineStr[i].split("\t");
                for (int i1 = 0; i1 < split.length; i1++) {
                    student = new Student(new Integer(split[0]),split[1],split[2],new Integer(split[3]),simpleDateFormat.parse(split[4]));
                }
                list.add(student);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {//关闭资源
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

    /**
     *
     * @param list
     * @return int
     * 题目(二)需求：(10分)
     *             1.将List<Student> list集合中的Student对象持久到数据库,并返回“成功”插入的记录总数count，
     *             2.在com.hp.school.test.ExamTest测试类的test_addStudent()方法中"*按要求*"完成测试
	 *				（提示：com.hp.school.utils.JDBCUtil类中封装了静态通用增删改方法）
     */
    @Override
    public int addStudent(List<Student> list) {
        int count =0;//用于记录成功插入记录总数
                /*在此补充业务代码*/
        for (int i = 0; i < list.size(); i++) {
            Student student = list.get(i);
            count += JDBCUtil.update("insert into student values(?,?,?,?,?)",new Object[]{student.getStudentNo(),student.getStudentName(),student.getSex(),student.getGradeId(),student.getBorndate()});
        }

        JDBCUtil.close();//关闭资源
        return count;
    }

    /**
     *
     * @param studentNo
     * @return student
     *
     * 题目(三)需求：(15分)
     *      1.根据学号查询学生信息，并以Student对象的形式返回
     *      2.在com.hp.school.test.ExamTest测试类的test_getStudentBySno()方法中"*按要求*"完成测试
     */
    @Override
    public Student getStudentBySno(int studentNo) {
        Student student=null;
            /*补全SQL语句*/
        String sql="select *from student where studentno=?";
        try {
            ResultSet rs = JDBCUtil.query( sql, new Object[]{studentNo} );
            if (rs.next()) {
                /*补全代码：如果查询出结果，将结果封装成Student对象*/
               while (rs.next()){
                   student = new Student(rs.getInt(1),
                                         rs.getString(2),
                                         rs.getString(3),
                                         rs.getInt(4),
                                         rs.getDate(5));
               }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close();
        }
        return student;
    }

    /**
     *
     * @param startDate
     * @param endDate
     * @return
     * 题目(四)需求：(15)
     *      1.查询符合时间区间生日的学生信息，并保存到List<Student> list集合中
     *      2.在com.hp.school.test.ExamTest测试类的 test_getStudentsByDate()方法中"*按要求*"完成测试
     */
    @Override
    public List<Student> getStudentsByDate(Date startDate, Date endDate) {
        List<Student> list=new ArrayList<>();//用于保存Student对象的集合
        Student student=null;
        try {
            /*SQL编写：获取生日在startDate和endDate区间的学生信息*/
            String sql="select * from student where borndate>? and borndate<?";
            ResultSet rs = JDBCUtil.query( sql, new Object[]{startDate,endDate} );
          /*代码补全：解析结果集，将结果集每一条记录封装成Student对象，并添加到List集合中*/
            if (rs != null) {
                while (rs.next()){
                    student = new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDate(5));
                    list.add(student);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close();//关闭资源
        }

        return list;
    }



    /**
     *
     * @param gradeId
     * @return
     * 题目(五)需求：(10)
     *      1.根据年级编号统计该年级男女数量各是多少
     *      2.将统计结果储存到Map<String, Integer>集合中
     *                 例如：     男       10
     *                          女       11
     *      3.在com.hp.school.test.ExamTest测试类的test_countSexByGradeId(int gradeId)方法里"*按要求*"完成测试
     *
     */
    @Override
    public Map<String, Integer> countSexByGradeId(int gradeId) {
        Map<String, Integer> map=new HashMap();
        /*SQL编写：根据年级编号统计该年级男女总数各是多少*/
        String sql="select sex,count(sex) from student where gradeid=? GROUP BY sex";
        try {
            ResultSet rs = JDBCUtil.query( sql, new Object[]{gradeId} );
            while(rs.next()){
               /*代码补全：将统计结果放到Map集合中*/
                map.put(rs.getString(1),rs.getInt(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close();
        }
        return map;
    }

    /**
     * 题目(六)需求：(10)
     *      1.学校要删除4年级"预备年级"，但是4年级有一位学生“严峻，学号为10020”，需要将这位学生转移到2年级
     *      (已知grade表中gradeid字段为主键,student表中gradeid字段外键关联引用了grade表的主键)
     *      2.在com.hp.school.test.ExamTest测试类的 test_removeGradeById()测试方法里“*按要求*”完成测试
     * @return
     */
    @Override
    public boolean removeGradeById() {
        boolean flag=false;
            /*按需求补全方法*/
        String sql = "update student set gradeid=2 where studentno=10020";
        int update = JDBCUtil.update(sql, null);
        if (update != 0) {
            flag=true;
        }else{
            flag=false;
        }
        return flag;
    }

    /**
     * 题目(七)需求：(15)
     *      1.获取年龄大于age的"学生姓名，年级名称"，并将结果集保存到map集合(学生姓名-年级名称)，返回map
     *      2.在com.hp.school.test.ExamTest测试类的test_getSNameAndGName(int age)方法中"*按要求*"完成测试
     * @param age
     * @return
     */
    @Override
    public Map<String, String> getSNameAndGName(int age) {
        Map<String, String> map=new HashMap<>();
        /*SQL编写：获取年龄大于age的学生姓名、年级名称*/

        String sql="SELECT studentname,gradeid FROM student WHERE (2021-YEAR(borndate))<?";
        try {
            ResultSet rs = JDBCUtil.query( sql, new Object[]{age} );
            while (rs.next()){
              /*将结果集中内容以键值对形式保存到map集合*/
                map.put(rs.getString(1),rs.getString(2));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close();
        }
        return map;
    }
}
