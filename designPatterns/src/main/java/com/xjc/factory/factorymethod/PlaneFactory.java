package com.xjc.factory.factorymethod;

/**
 * 工厂方法
 */
public class PlaneFactory {
    public Movable create() {
        return new Plane();
    }
}
