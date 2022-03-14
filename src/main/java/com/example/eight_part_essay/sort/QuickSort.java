package com.example.eight_part_essay.sort;

import java.util.Arrays;

/**
 * @author: zy
 * @date: 2022/3/14 20:04
 * @since JDK 1.8
 * 不稳定排序
 * 平均时间复杂度O(nlog n)   空间复杂度O(1)
 */

public class QuickSort {
    protected static void quickSort(int[] arr, int left, int right) {
        int i, j, index;
        if (left > right) {
            return;
        }
        i = left;
        j = right;
        index = arr[i];
        while (i < j) {
            //先从右边进行扫描，找到大于基准值的元素
            while (i < j && arr[j] >= index) {
                j--;
            }
            //找到之后交换
            if (i < j) {
                arr[i++] = arr[j];
            }
            //然后从左边扫描，找到小于基准值的元素
            while (i < j && arr[i] < index) {
                i++;
            }
            //找到之后交换
            if (i < j) {
                arr[j--] = arr[i];
            }
        }
        arr[i] = index;
        // 对低子表进行递归排序
        quickSort(arr, left, i - 1);
        // 对高子表进行递归排序
        quickSort(arr, i + 1, right);
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 1, 8, 6};
        quickSort(arr, 0, 4);
        System.out.println(Arrays.toString(arr));
    }
}
