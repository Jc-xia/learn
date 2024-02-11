package com.xjc.factory.abstractfactory;

public class Gun extends Weapon {
    @Override
    void hit() {
        System.out.println("gun shoot");
    }
}
