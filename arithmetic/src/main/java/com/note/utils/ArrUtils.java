package com.note.utils;

import java.util.Arrays;

public class ArrUtils {

    public static int[] randomArray(int maxSize, int maxValue) {
        //Math.random() [0,1)
        //N * Math.random() [0,N)
        //(int) N * Math.random() [0,N-1]
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];//长度随机
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue) * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        int[] arr1 = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr1[i] = arr[i];
        }
        return arr1;
    }

    public static boolean isEquals(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    /**
     * 生成随机数组
     *
     * @param maxKinds 数的种类
     * @param range 数的范围
     * @param k
     * @param m
     * @return
     */
    public static int[] randomArr(int maxKinds, int range, int k, int m) {
        return null;
    }

    public static int randomNum(int range){
        //[1, range] - [1, range]
        return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
    }

    public static void print(int[] nums){
        System.out.println(Arrays.toString(nums));
    }
}

