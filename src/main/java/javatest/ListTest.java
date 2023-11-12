package javatest;

import java.util.ArrayList;

public class ListTest {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        System.out.println(strings.isEmpty());
        System.out.println(strings.contains("aaa"));
    }
}
