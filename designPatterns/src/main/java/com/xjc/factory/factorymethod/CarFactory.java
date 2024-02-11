package com.xjc.factory.factorymethod;

/**
 * 工厂方法
 */
public class CarFactory {

    public Movable createCar() {
        return new Car();
    }
}
