package com.xjc.factory.abstractfactory;

public class ModernFactory extends AbstractFactory {
    @Override
    Food createFood() {
        return new Bread();
    }

    @Override
    Weapon createWeapon() {
        return new Gun();
    }

    @Override
    Vehicle createVehicle() {
        return new Car();
    }
}
