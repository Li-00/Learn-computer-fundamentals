package com.example.eight_part_essay.sort;

import java.util.Arrays;

/**
 * @author: zy
 * @date: 2022/3/14 19:56
 * @since JDK 1.8
 * 稳定排序
 * 时间复杂度 O(n^2)   空间复杂度O(1)
 */

public class BubbleSort{

    /**
     *从小到大排序
     * @param arr
     * @return arr
     */
    protected static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean flag = true;
            for (int j = 1; j < arr.length; j++) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 8, 1};
        System.out.println(Arrays.toString(bubbleSort(arr)));
    }
}
