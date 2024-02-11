package com.xjc.strategy;

import lombok.Data;

/**
 * @author JC
 * @create 2024/2/4
 */
@Data
public class Cat implements Comparable<Cat> {
    private int height;
    private int weight;

    public Cat(int height, int weight) {
        this.height = height;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "height=" + height +
                ", weight=" + weight +
                '}';
    }


    @Override
    public int compareTo(Cat cat) {
        if (cat.height > this.height) {
            return -1;
        } else if (cat.height < this.height) {
            return 1;
        }
        return 0;
    }
}
