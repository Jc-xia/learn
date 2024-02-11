package com.xjc.strategy;

/**
 * @author JC
 * @create 2024/2/4
 */
public class CatHeightComparator implements Comparator<Cat> {
    @Override
    public int compare(Cat cat1, Cat cat2) {
        if (cat1.getHeight() < cat2.getHeight()) {
            return -1;
        } else if (cat1.getHeight() > cat2.getHeight()) {
            return 1;
        }
        return 0;
    }
}
