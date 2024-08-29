package com.mybatis.learning;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//实现SqlSession的封装，这个是一个工具类
public class SqlSessionUtil {
    //使用static确保了在整个类的实例中只创建一个sqlSessionFactory对象；类似与单例模式；
    // sqlSessionFactory对象的创建设计读取配置，建立数据库连接；避免多重创建
    public static SqlSessionFactory sqlSessionFactory;
    //代码块static，类加载时加载，顺序优于构造函数
    static {
        try {
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        }
        catch (Exception e){
            e.printStackTrace(); //打印异常
        }
    }
    //static方法的声明，是为了使用class.method()调用；不用实例化；工具类的特性
    // 每调用一次openSession()可获取一个新的会话，该会话支持自动提交。
    public static SqlSession openSession(){
        return sqlSessionFactory.openSession(true);
    }
}
