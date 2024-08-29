package com.mybatis.learning.pojo;

import com.mybatis.learning.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class CarMapperTest {
    @Test
    public void mybatis_insert_01_test() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        int count = sqlSession.insert("insertCar");
        System.out.println("update records:" + count);
    }
    @Test
    public void mybatis_parameter_01_test() {
        //通过map完成传参
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //注意这里为）Object类，是所有类的祖宗（根类），因此它可以表示任何对象；不是）Objects，这是一个工具类
        Map<String, Object> map = new HashMap<>();
        map.put("carNum", "103");
        map.put("brand", "奔驰E300L");
        map.put("guidePrice", 50.3);
        map.put("produceTime", "2020-10-01");
        map.put("carType", "燃油车");
        int count = sqlSession.insert("insertCarByMap",map);
        System.out.println("update records:"+count);
    }

    //delete相关测试
    @Test
    public void mybatis_delete_01_test(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        int count = sqlSession.insert("deleteByCarNum","103");
        System.out.println("update records:"+count); //这个记录数不对，原因未知
    }

    //select
    @Test
    public void  mybatis_select_01_test(){
        // 获取SqlSession对象
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // 执行SQL语句
        Object car = sqlSession.selectOne("selectCarById", 1);
        System.out.println(car); //car对象 com.mybatis.learning.pojo.Car@455351c4
    }
}
