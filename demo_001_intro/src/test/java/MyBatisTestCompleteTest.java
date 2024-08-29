import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

public class MyBatisTestCompleteTest {
    @Test
    public void complete_01_test() {
        System.out.println("开始执行数据库操作");
        SqlSession sqlSession = null; //防止出现异常在finally中没有创建对象
        try {
            //1 创建Builder
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder =new SqlSessionFactoryBuilder();
            //2 builder创建factory
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory =sqlSessionFactoryBuilder.build(is);
            //3 创建SqlSession对象
            sqlSession = sqlSessionFactory.openSession();
            // 4.执行SQL
            int count = sqlSession.insert("insertCar");
            // 5 提交事务
            sqlSession.commit();
            System.out.println("执行更新成功，更新记录数："+count);
            // 6 关闭连接
            sqlSession.close();
        }
        //异常回滚
        catch (Exception e){
            if (sqlSession != null){
                System.out.println("执行更新失败，将回滚");
                sqlSession.rollback();
            }
    }
        // 异常执行强制关闭
        finally {
           if (sqlSession != null){
               sqlSession.close();
           }
        }
        }
}
