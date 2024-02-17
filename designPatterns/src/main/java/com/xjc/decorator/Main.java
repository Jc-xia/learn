package com.xjc.decorator;

public class Main {
    public static void main(String[] args) {
        CommonCoffee commonCoffee = new CommonCoffee();
        CoffeeDecorator coffeeDecorator = new MilkCoffeeDecorator(commonCoffee);
        coffeeDecorator.makeCoffee();
    }
}
