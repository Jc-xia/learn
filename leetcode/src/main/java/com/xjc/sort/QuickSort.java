package com.xjc.sort;

import java.util.Arrays;

/**
 * @author JC
 * @create 2023/11/8
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 5, 6, 7};
        quickSort3(ints, 0, ints.length - 1);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * ↑ 代表基准值位置指针 ，👆 表示可交换修改的位置
     * 7  5  3  6  2  1		基准值随机，为6，从左往右遍历，以6为分界点
     * ↑👆
     * 6  5  3  7  2  1		7>6,发生交换,同时指针位置-1
     * 👆    ↑
     * 3  5  6  7  2  1		交换基准值指针位置元素和基准值
     * ↑👆
     * 当遍历的指针大于基准值位置时，逻辑改变，基准值指针+1
     */
    static void quickSort3(int[] a, int l, int h) {
        //单指针，基准值位置随机
        if (l < h) {
            int n = (int) (1 + Math.random() * (h - 1));
            for (int i = l; i < h; i++) {
                if (i < n) {
                    if (a[i] > a[n]) {
                        swap(a, i, n);
                        n--;
                        swap(a, i, n);
                    }
                } else {
                    if (a[i] < a[n]) {
                        swap(a, i, n);
                        n++;
                        swap(a, i, n);
                    }
                }
            }
            quickSort3(a, l, n);
            quickSort3(a, n + 1, h);
        }
    }

    /**
     * 改良后，可以将基准值设置为一侧的顶点，省去遍历时遍历指针大于或小于基准值位置的判断，保持逻辑一致，修改后代码如下
     */
    static void quickSort4(int[] a, int l, int h) {
        //单指针，基准值位置=1
        if (l < h) {
            int n = l;
            for (int i = l; i < h; i++) {
                if (a[i] < a[n]) {
                    swap(a, i, n);
                    n++;
                    swap(a, i, n);
                }
            }
            quickSort4(a, l, n);
            quickSort4(a, n + 1, h);
        }
    }

    /**
     * 进一步优化，基准值不参与交换，省去基准值位置变化后的修改，
     * 基准值为开头，交换的是基准值后面的元素，所以基准值所指位置是小于基准值的，直接交换就行
     */
    static void quickSort6(int[] a, int l, int h) {
        if (l < h) {
            int n = l;
            for (int i = l + 1; i < h; i++) {
                if (a[i] < a[l]) {
                    swap(a, n + 1, i);
                    n++;
                }
            }
            swap(a, l, n);
            quickSort6(a, l, n);
            quickSort6(a, n + 1, h);
        }
    }

    /**
     * 基准值为末尾
     */
    static void quickSort7(int[] a, int l, int h) {
        if (l < h) {
            int i, j;
            for (i = l, j = l; j < h - 1; j++) {
                if (a[j] <= a[h - 1]) {
                    swap(a, i++, j);
                }
            }
            swap(a, i, j);
            quickSort7(a, l, j);
            quickSort7(a, j + 1, h - 1);
        }
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

}
