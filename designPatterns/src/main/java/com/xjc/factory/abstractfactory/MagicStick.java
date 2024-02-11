package com.xjc.factory.abstractfactory;

public class MagicStick extends Weapon {
    @Override
    void hit() {
        System.out.println("magic attack");
    }
}
