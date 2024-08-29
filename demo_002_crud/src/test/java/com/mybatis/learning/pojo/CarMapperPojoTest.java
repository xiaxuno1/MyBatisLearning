package com.mybatis.learning.pojo;

import com.mybatis.learning.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CarMapperPojoTest {
    @Test
    public void mybatis_insert_02_test() {
        //通过对象传参ORM
        // 创建POJO，封装数据
        Car car = new Car();  //Car内的属性名称要和设置的参数名称一致
        car.setCarNum("103");
        car.setBrand("奔驰C200");
        car.setGuidePrice(33.23);
        car.setProduceTime("2020-10-11");
        car.setCarType("燃油车");
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //注意这里为）Object类，是所有类的祖宗（根类），因此它可以表示任何对象；不是）Objects，这是一个工具类
        int count = sqlSession.insert("insertCarByPOJO",car);
        System.out.println("update records:"+count);
    }

    //测试update by pojo
    @Test
    public void mybatis_update_01_test() {
        //通过对象传参ORM
        // 创建POJO，封装数据
        Car car = new Car();  //Car内的属性名称要和设置的参数名称一致
        car.setCarNum("999"); //将102
        car.setBrand("奔驰C200");
        car.setGuidePrice(33.23);
        car.setProduceTime("2020-10-11");
        car.setCarType("燃油车");
        car.setId(1L); //将id为1的数据更新,1L表示long int
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //注意这里为）Object类，是所有类的祖宗（根类），因此它可以表示任何对象；不是）Objects，这是一个工具类
        int count = sqlSession.insert("updateCarByPOJO",car);
        System.out.println("update records:"+count);
    }

    //select
    @Test
    public void mybaitis_select_01_test(){
        // 获取SqlSession对象
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // 执行SQL语句
        Object car = sqlSession.selectOne("selectCarById", 1);
        System.out.println(car); //返回的为一个对象 com.mybatis.learning.pojo.Car@1eba372c
    }

    @Test
    public void mybatis_select_all_test(){
        // 获取SqlSession对象
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // 执行SQL语句，返回多条结果类型为 List
        List<Object> cars = sqlSession.selectList("selectCarAll");
        // 输出结果
        cars.forEach(car -> System.out.println(car));
    }
}
