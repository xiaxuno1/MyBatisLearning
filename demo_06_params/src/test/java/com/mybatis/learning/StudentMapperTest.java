package com.mybatis.learning;

import com.mybatis.learning.mapper.StudentMapper;
import com.mybatis.learning.pojo.Student;
import com.mybatis.learning.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 测试mybatis传参
* */
public class StudentMapperTest {
    /*
     * mybatis传入单个参数接口，参数的类型都是简单类型
     * */
    @Test
    public void selectByIdTest(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class); //生成实现类，实现方法
        List<Student> students =studentMapper.selectById(2L);  //类型为long
        //匿名函数遍历
        students.forEach(System.out::println);
       // sqlSession.close();
    }

    @Test
    public void selectByNameTest(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class); //生成实现类，实现方法
        List<Student> students =studentMapper.selectByName("李四");  //类型为long
        //匿名函数遍历
        students.forEach(System.out::println);
        //sqlSession.close();
    }

    @Test
    public void selectByBirthTest() throws Exception {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class); //生成实现类，实现方法
        //建立时间类型对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birth = sdf.parse("2000-8-1");
        List<Student> students =studentMapper.selectByBirth(birth);  //类型为long
        //匿名函数遍历
        students.forEach(System.out::println);
        //sqlSession.close();  //close后会报错，原因未知，多半同test的机制有关
    }

    @Test
    public void selectBySexTest(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class); //生成实现类，实现方法
        //character
        Character sex = Character.valueOf('女');
        List<Student> students =studentMapper.selectBySex(sex);  //类型为long
        //匿名函数遍历
        students.forEach(System.out::println);
        //sqlSession.close();
    }

//    List<Student> selectById(Long id);
//    List<Student> selectByName(String name);
//    List<Student> selectByBirth(Date birth);
//    List<Student> selectBySex(Character sex);

    /*
     *mybatis传入单个参数接口，参数类型为map对象
     * */
    @Test
    //int insertStudentByMap(Map<String, Object> map);
    public void insertStudentByMapTest(){
        //创建新的map对象
        Map<String,Object> map = new HashMap<>();
        map.put("姓名","李九");  //key要与mapper.xml中定义的一致
        map.put("年龄", 20);
        map.put("身高", 1.81);
        map.put("性别", '男');
        map.put("生日", new Date());
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class); //生成实现类，实现方法
        studentMapper.insertStudentByMap(map);
        sqlSession.commit();
    }
    /*
     *mybatis传入单个参数接口，参数类型为pojo对象
     * */
   // int insertStudentByPOJO(Student student);
    @Test
    public void testInsertStudentByPOJO(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        // POJO对象
        Student student = new Student();
        student.setName("张飞");
        student.setAge(50);
        student.setSex('女');
        student.setBirth(new Date());
        student.setHeight(10.0);

        mapper.insertStudentByPOJO(student);
        sqlSession.commit();
        //sqlSession.close();
    }
    /*
     * 多参数类型，通过arg0,arg1或者param1,param2传递参数
     *     mybatis框架会自动创建一个Map集合。并且Map集合是以这种方式存储参数的：
     *    map.put("arg0", name);
     *     map.put("arg1", sex);
     *     map.put("param1", name);
     *     map.put("param2", sex);
     * */
    //List<Student> selectByNameAndSex(String name, Character sex);
    @Test
    public void testSelectByNameAndSex(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectByNameAndSex("张三", '男');
        students.forEach(student -> System.out.println(student));
        //sqlSession.close();
    }
    /*
     * 多参数类型，通过@Param()注解声明参数
     *  mybatis框架底层的实现原理：
     *  map.put("name", name);
     *  map.put("sex", sex);
     * */
    //List<Student> selectByNameAndSex2(@Param("name") String name, @Param("sex") Character sex);
    @Test
    public void testSelectByNameAndSex2(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // mapper实际上指向了代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        // mapper是代理对象
        // selectByNameAndSex2是代理方法
        List<Student> students = mapper.selectByNameAndSex2("张三", '男');
        students.forEach(student -> System.out.println(student));
        //sqlSession.close();
    }
}
