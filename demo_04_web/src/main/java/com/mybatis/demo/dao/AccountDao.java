package com.mybatis.demo.dao;

import com.mybatis.demo.pojo.Account;

//Account的CRUD，强调一下：DAO对象中的任何一个方法和业务不挂钩。没有任何业务逻辑在里面。
public interface AccountDao {
    /*
    * 根据账号查询账户信息
    * */
    Account selectByActno(String actno);
    /*
    * 更新账户信息
    * 1表示更新成功，其他值表示失败。
    * */
    int updateByActno(Account act);
}
