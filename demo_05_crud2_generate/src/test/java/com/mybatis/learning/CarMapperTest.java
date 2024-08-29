package com.mybatis.learning;

import com.mybatis.learning.mapper.CarMapper;
import com.mybatis.learning.pojo.Car;
import com.mybatis.learning.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CarMapperTest {
    @Test
    public void testInsert() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // 面向接口获取接口的代理对象
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        //创建待插入对象
        Car car = new Car(null, "4444", "奔驰C200", 32.0, "2000-10-10", "新能源");
        int count = mapper.insert(car); //方法与CarMapper.xml定义的一致
        System.out.println(count);
        sqlSession.commit();
    }
    @Test
    public void testDeleteById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int count = mapper.deleteById(154L);
        System.out.println(count);
        sqlSession.commit();
    }
    @Test
    public void testUpdate(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(155L, "2222", "凯美瑞222", 3.0, "2000-10-10", "新能源");
        mapper.update(car);
        sqlSession.commit();
    }
    @Test
    public void testSelectById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectById(155L);
        System.out.println(car);
    }
    @Test
    public void testSelectAll(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAll();
        cars.forEach(System.out::println);
    }
}
