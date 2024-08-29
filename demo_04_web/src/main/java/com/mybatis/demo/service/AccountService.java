package com.mybatis.demo.service;


import com.mybatis.demo.exception.MoneyNotEnoughException;
import com.mybatis.demo.exception.TransferException;

/**
 * 注意：业务类当中的业务方法的名字在起名的时候，最好见名知意，能够体现出具体的业务是做什么的。
 * 账户业务类
 */
public interface AccountService {

    /**
     * 账户转账业务。
     * @param fromActno 转出账号
     * @param toActno 转入账号
     * @param money 转账金额
     */
    void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, TransferException;

}
