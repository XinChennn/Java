package com.hp.school.test;
import com.hp.school.dao.StudentDao;
import com.hp.school.dao.impl.StudentDaoImpl;
import com.hp.school.pojo.Student;
import com.hp.school.utils.JDBCUtil;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExamTest {
    //这个对象可以对学生信息进行CURD操作
    private StudentDao studentDao=new StudentDaoImpl();
    //这个对象可以完成时间格式的转换
    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

    /**
     *题目(一)测试要求：
     *    1.调用studentDao对象的List<Student> studentTxtToList(String path)方法获取list集合
     *    2.遍历list集合
     */
    @Test
    public void test_studentTxtToList(){
        String path="D:\\StudentList.txt";//StudentList.txt路径
                 /*题目(一)测试补全测试代码*/
        List<Student> students = studentDao.studentTxtToList();
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i));
        }

    }

    /**
     *题目(二) 测试要求：
     *      1.调用studentDao对象的List<Student> studentTxtToList(String path)方法获取list集合
     *      2.将获取的list集合里的Student对象持久到数据库，并打印“成功”添加的记录总数
     *          打印格式：成功添加N条数据
     */
    @Test
    public void test_addStudent(){
                /*题目(二)测试补全测试代码*/
        int count = 0;
        List<Student> students = studentDao.studentTxtToList();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            count += JDBCUtil.update("insert into student values(?,?,?,?,?)",new Object[]{student.getStudentNo(),student.getStudentName(),student.getSex(),student.getGradeId(),student.getBorndate()});
        }
        System.out.println("成功添加"+count+"条数据");
    }

    /**
     * 题目(三)测试需求：
     *      1.调用studentDao对象的getStudentBySno(int stdentno)方法，并传入一个学号10013，获取该学生对象
     *      2.并对此学生进行详细描述，并将描述信息输出到控制台。
     *      “******严格按照以下格式显示学生信息*****”  特别是时间格式！！！
     *      显示格式要求：学号为00000的详细信息：姓名 张三,男,今年21岁,出生于2000-2-18,来自1年级
     */
    @Test
    public void test_getStudentBySno(){
        /*题目(三)测试补全测试代码*/
        Student s = studentDao.getStudentBySno(10013);
        Date date=new Date();//当前时间
        //被废弃的 .getYear() 是从1900年到现在的日期的差值
        String stuInfo=  "学号为"+s.getStudentNo()+"的详细信息：姓名 "+s.getStudentName()+","+s.getSex()+",今年"+(date.getYear()-s.getBorndate().getYear())
                +"岁,出生于"+simpleDateFormat.format(s.getBorndate())+",来自"+s.getGradeId()+"年级";//学生描述信息字符串
        System.out.println(stuInfo);
    }

    /**
     * @throws ParseException
     * 题目(四)测试需求：
     *      1.已知两个字符串格式时间
     *      2.通过调用"getStudentsByDate(Date startDate, Date endDate)"方法查询这个两个时间之间生日的学生信息，并获取List集合
     *      3.遍历list集合
     */
    @Test
    public void test_getStudentsByDate() throws ParseException {
        String sta="1993-01-01";
        String end="1994-2-14";
        /*题目(四)测试代码补全*/
        List<Student> studentsByDate = studentDao.getStudentsByDate(simpleDateFormat.parse(sta), simpleDateFormat.parse(end));
        for (int i = 0; i < studentsByDate.size(); i++) {
            System.out.println(studentsByDate.get(i));
        }

    }

    /**
     *题目(五)测试要求：
     *      1.调用studentDao对象的countSexByGradeId(int gradeId)方法获取储存有统计年级男女个数的Map集合
     *      2.将统计信息打印到控制台
     *          格式：男生总数：2
     *               女生总数：2
     */
    @Test
    public void test_countSexByGradeId(){
                 /*题目(五)测试代码补全*/
        Map<String, Integer> map = studentDao.countSexByGradeId(1);
        System.out.println("男生总数："+map.get("男"));
        System.out.println("女生总数："+map.get("女"));
    }

    /**
     * 题目(六)测试需求：
     *      1.调用studentDao对象的removeGradeById()方法将“严峻同学转班”，并删除4年级
     *      2.判断是否操作成功，如果操作成功打印“操作成功”，否则打印“操作失败”
     *      3.数据库最终结果：4年级被删除，“严峻，学号为10020”的学生转移到2年级
     */
    @Test
    public void test_removeGradeById(){
            /*题目(六)测试代码补全*/
        boolean b = studentDao.removeGradeById();
        System.out.println(b?"操作成功":"操作失败");
    }

    /**
     * 题目(七)测试需求：
     *      1.调用studentDao对象的getSNameAndGName( int age )方法，获取符合条件的StudentMap集合
     *      2.展示map集合内容
     */
    @Test
    public void test_getSNameAndGName(){
        JDBCUtil.close();
        Map<String, String> sNameAndGName = studentDao.getSNameAndGName( 27 );
        System.out.println(sNameAndGName);
    }
}
