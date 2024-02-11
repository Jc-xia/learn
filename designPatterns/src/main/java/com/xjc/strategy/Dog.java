package com.xjc.strategy;

import lombok.Data;

/**
 * @author JC
 * @create 2024/2/4
 */
@Data
public class Dog {
    private int food;

    public Dog(int food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "food=" + food +
                '}';
    }
}
