package com.mybatis.demo.dao.Impl;


import com.mybatis.demo.dao.AccountDao;
import com.mybatis.demo.pojo.Account;
import com.mybatis.demo.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSession;

public class AccountDaoImpl implements AccountDao {
    /*
     * 实现查询指定账户
     * */
    @Override
    public Account selectByActno(String actno) {
        SqlSession sqlSession = SqlSessionUtil.openSession(); //类名.方法调用不用new对象
        return (Account) sqlSession.selectOne("selectByActno", actno); //mybatis调用方法，传参

    }

    @Override
    public int updateByActno(Account act) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //int count = sqlSession.update("account.updateByActno", act);
        //return count;
        return sqlSession.update("account.updateByActno", act);
    }
}
