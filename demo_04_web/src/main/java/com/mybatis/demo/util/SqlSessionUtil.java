package com.mybatis.demo.util;

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
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        }
        catch (Exception e){
            e.printStackTrace(); //打印异常
        }
    }
    //static方法的声明，是为了使用class.method()调用；不用实例化；工具类的特性
    //采用Threadlocal保证了一个线程只能使用SqlSession实例，防止多次创建session，导致事务错误
    //因为ThreadLocal在每个线程中对该变量会创建一个副本，即每个线程内部都会有一个该变量，
    // 且在线程内部任何地方都可以使用，线程之间互不影响，这样一来就不存在线程安全问题，也不会严重影响程序执行性能
    private static final  ThreadLocal<SqlSession> local = new ThreadLocal<>(); //private保证不能被类外调用，static保证local只有一个，即local类被共享
    // 每调用一次openSession()可获取一个新的会话，该会话支持自动提交。
    public static SqlSession openSession(){
        SqlSession sqlSession=local.get();
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
            // 将sqlSession对象绑定到当前线程上。
            local.set(sqlSession);
        }
        return sqlSession;
    }
    /**
     * 关闭SqlSession对象(从当前线程中移除SqlSession对象。)
     * @param sqlSession
     */
    public static void close(SqlSession sqlSession){
        if (sqlSession != null) {
            sqlSession.close();
            // 注意移除SqlSession对象和当前线程的绑定关系。
            // 因为Tomcat服务器支持线程池。也就是说：用过的线程对象t1，可能下一次还会使用这个t1线程。
            local.remove();
        }
    }
}
