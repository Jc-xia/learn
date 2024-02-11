package com.xjc.factory.factorymethod;

/**
 * 工厂方法，每个产品都有自己的工厂类
 * 通过实现movable接口，达到新增任意产品的目的
 * 通过新增对应的工厂类，使任意产品的新增过程定制化
 * 优点：更符合开闭原则，增加一个产品类，则只需要实现其他具体的产品类和具体的工厂类即可；符合单一职责原则，每个工厂只负责生产对应的产品
 * 缺点：增加一个产品，就需要实现对应的具体工厂类和具体产品类；每个产品需要有对应的具体工厂和具体产品类
 * <p>
 * 当各自的工厂类，继承一个公共的抽象工厂类时，工厂方法模式 就成了 产品族只有一个产品的 抽象工厂模式
 */
public class Main {
    public static void main(String[] args) {
        Movable vehicle = new CarFactory().createCar();
//        Movable vehicle = new PlaneFactory().createCar();
        vehicle.run();
    }
}
