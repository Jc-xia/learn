package com.xjc.decorator;

public class CommonCoffee implements Coffee {
    @Override
    public void makeCoffee() {
        System.out.println("make common coffee");
    }
}
