package com.mybatis.learning.mapper;

import com.mybatis.learning.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

//mybatis的接口，实现CRUD抽象；之前叫Dao，mybatis中习惯称为mapper
public interface StudentMapper {
    /*
    * mybatis传入单个参数接口，参数的类型都是简单类型
    * */
    List<Student> selectById(Long id);
    List<Student> selectByName(String name);
    List<Student> selectByBirth(Date birth);
    List<Student> selectBySex(Character sex);

    /*
    *mybatis传入单个参数接口，参数类型为map对象
    * */
    int insertStudentByMap(Map<String, Object> map);

    /*
     *mybatis传入单个参数接口，参数类型为pojo对象
     * */
    int insertStudentByPOJO(Student student);

    /*
    * 多参数类型，通过arg0,arg1或者param1,param2传递参数
    *     mybatis框架会自动创建一个Map集合。并且Map集合是以这种方式存储参数的：
     *    map.put("arg0", name);
     *     map.put("arg1", sex);
     *     map.put("param1", name);
     *     map.put("param2", sex);
    * */
    List<Student> selectByNameAndSex(String name, Character sex);

    /*
     * 多参数类型，通过@Param()注解声明参数
     *  mybatis框架底层的实现原理：
     *  map.put("name", name);
     *  map.put("sex", sex);
     * */
    List<Student> selectByNameAndSex2(@Param("name") String name, @Param("sex") Character sex);
}
