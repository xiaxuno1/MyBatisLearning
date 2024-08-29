package com.mybatis.dao;


//Account的CRUD，强调一下：DAO对象中的任何一个方法和业务不挂钩。没有任何业务逻辑在里面。
public interface AccountDao {
    void delete();
    int insert(String actno);
    int update(String actno, Double balance);
    String selectByActno(String actno);
}
