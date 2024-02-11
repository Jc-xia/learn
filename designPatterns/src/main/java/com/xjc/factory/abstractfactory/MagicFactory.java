package com.xjc.factory.abstractfactory;

public class MagicFactory extends AbstractFactory {
    @Override
    Food createFood() {
        return new MashRoom();
    }

    @Override
    Weapon createWeapon() {
        return new MagicStick();
    }

    @Override
    Vehicle createVehicle() {
        return new Broom();
    }
}
