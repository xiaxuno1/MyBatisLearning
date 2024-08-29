package com.mybatis.demo.service.Impl;

import com.mybatis.demo.dao.AccountDao;
import com.mybatis.demo.exception.MoneyNotEnoughException;
import com.mybatis.demo.exception.TransferException;
import com.mybatis.demo.pojo.Account;
import com.mybatis.demo.service.AccountService;
import com.mybatis.demo.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import com.mybatis.demo.dao.AccountDao;

public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao = SqlSessionUtil.openSession().getMapper(AccountDao.class);
    @Override
    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, TransferException {
        // 添加事务控制代码
        SqlSession sqlSession = SqlSessionUtil.openSession();

        // 1. 判断转出账户的余额是否充足(select)
        Account fromAct =accountDao.selectByActno(fromActno);
        if (fromAct.getBalance() < money) {
            // 2. 如果转出账户余额不足，提示用户
            throw new MoneyNotEnoughException("对不起，余额不足！");
        }
        // 3. 如果转出账户余额充足，更新转出账户余额(update)
        // 先更新内存中java对象account的余额
        Account toAct = accountDao.selectByActno(toActno);
        fromAct.setBalance(fromAct.getBalance() - money);
        toAct.setBalance(toAct.getBalance() + money);
        int count = accountDao.updateByActno(fromAct);

        // 模拟异常
        /*String s = null;
        s.toString();*/

        // 4. 更新转入账户余额(update)
        count += accountDao.updateByActno(toAct);
        if (count != 2) {
            throw new TransferException("转账异常，未知原因");
        }

        // 提交事务
        sqlSession.commit();
        // 关闭事务
        SqlSessionUtil.close(sqlSession);
    }

}
