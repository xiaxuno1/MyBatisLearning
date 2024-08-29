package com.mybatis.demo.pojo;
//mybatis的ORM
public class Account {
    private Long id;
    private String actno;
    private Double balance;

    public Long getId() {
        return id;
    }

    public String getActno() {
        return actno;
    }

    public Double getBalance() {
        return balance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setActno(String actno) {
        this.actno = actno;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Account() {
    }

    public Account(Long id, String actno, Double balance) {
        this.id = id;
        this.actno = actno;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return  "Account{" +
                "id=" + id +
                ", actno='" + actno + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
