package com.xjc.factory.simplefactory;

/**
 * 简单工厂，一个工厂生产多个产品
 * 可以通过实现movable接口，达到新增任意产品的目的
 * 优点：客户端与产品的创建分离，客户端不需要知道产品创建的逻辑，只需要消费该产品即可。
 * 缺点：工厂类集成了所有产品的创建逻辑，当工厂类出现问题，所有产品都会出现问题；还有当新增加产品都会修改工厂类，违背开闭原则
 */
public class Main {
    public static void main(String[] args) {
        VehicleFactory vehicleFactory = new VehicleFactory();
        Movable vehicle = vehicleFactory.createCar();
//        Moveable vehicle = vehicleFactory.createPlane();
        vehicle.run();
    }
}
