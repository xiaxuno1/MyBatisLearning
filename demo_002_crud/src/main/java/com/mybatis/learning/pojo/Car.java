package com.mybatis.learning.pojo;
//Cai关系数据对象的封装，对应t_car
public class Car {
    private Long id;
    private String carNum;
    private String brand;
    private Double guidePrice;
    private String produceTime;
    private String carType;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setGuidePrice(Double guidePrice) {
        this.guidePrice = guidePrice;
    }

    public void setProduceTime(String produceTime) {
        this.produceTime = produceTime;
    }

    public Long getId() {
        return id;
    }

    public String getCarNum() {
        return carNum;
    }

    public String getBrand() {
        return brand;
    }

    public Double getGuidePrice() {
        return guidePrice;
    }

    public String getProduceTime() {
        return produceTime;
    }

    public String getCarType() {
        return carType;
    }
    //构造函数
    public Car(Long id, String carNum, String brand, Double guidePrice, String produceTime, String carType) {
        this.id = id;
        this.carNum = carNum;
        this.brand = brand;
        this.guidePrice = guidePrice;
        this.produceTime = produceTime;
        this.carType = carType;
    }

    public Car() {
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    //重写toString方法，打印详细信息，否则println()返回的是对象
    // 默认情况下，Object 类的 toString 方法返回的是对象的类名和其哈希码，而不是对象的实际内容。

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carNum='" + carNum + '\'' +
                ", brand='" + brand + '\'' +
                ", guidePrice=" + guidePrice +
                ", produceTime='" + produceTime + '\'' +
                ", carType='" + carType + '\'' +
                '}';
    }
}
