package com.mybatis.learning.pojo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class CarMapperPojoTest {
   @Test
    public void config_test() throws Exception{
       //声明这个方法可能会抛出异常，可以在方法中定义Exception的异常
        // 准备数据
       Car car = new Car();
       car.setCarNum("133");
       car.setBrand("丰田霸道");
       car.setGuidePrice(50.3);
       car.setProduceTime("2020-01-10");
       car.setCarType("燃油车");
       // 两个数据库对应两个SqlSessionFactory对象，以此类推
       SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

       // 使用默认数据库
       SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
       SqlSession sqlSession = sqlSessionFactory.openSession(true);
       int count = sqlSession.insert("insertCar", car);
       System.out.println("插入了几条记录：" + count);

       // 使用指定数据库
       SqlSessionFactory sqlSessionFactory1 = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"), "production");
       SqlSession sqlSession1 = sqlSessionFactory1.openSession(true);
       int count1 = sqlSession1.insert("insertCar", car);
       System.out.println("插入了几条记录：" + count1);
   }
   }
