package com.xjc.factory.abstractfactory;

/**
 * 抽象工厂，侧重于产品族的概念，一组产品如Food，Weapon，Vehicle
 * 一个工厂生产一组产品，继承一个抽象的工厂
 * 不同产品族的产品继承各自的产品抽象类
 * 优点：1.当一个产品族中的多个对象被设计成一起工作时，它能保证客户端始终只使用同一个产品族中的对象。2.保证减少工厂类和具体产品的类添加。
 * 缺点：产品族扩展非常困难，要增加一个系列的某一产品，既要在抽象的 工厂和抽象产品里加代码，又要在具体的里面加代码。
 */
public class Main {

    public static void main(String[] args) {
        AbstractFactory abstractFactory = new ModernFactory();
//        AbstractFactory abstractFactory = new MagicFactory();
        Food food = abstractFactory.createFood();
        food.eat();
        Vehicle vehicle = abstractFactory.createVehicle();
        vehicle.run();
        Weapon weapon = abstractFactory.createWeapon();
        weapon.hit();
    }
}
