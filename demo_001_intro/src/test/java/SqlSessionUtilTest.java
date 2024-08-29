import com.mybatis.learning.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class SqlSessionUtilTest {
    @Test
    public void insert_test(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        int count = sqlSession.insert("insertCar");
        System.out.println("update records:"+count);

    }
}
