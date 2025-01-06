package com.note.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArrUtils {

    /**
     * 生成随机数组
     *
     * @param maxSize 数组最大长度
     * @param maxValue 样本最大值
     */
    public static int[] randomArray(int maxSize, int maxValue) {
        //[0, mazSize] 随机数组长度
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = randomNum(maxValue);
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
     * @param k 一个数出现k次
     * @param m 其他数出现m次
     */
    public static int[] randomArr(int maxKinds, int range, int k, int  m) {
        int[] nums = new int[k + (maxKinds - 1) * m];
        int idx = 0;

        //填充出现k次的数
        int kNum = randomNum(range);
        for (; idx < k; idx++) {
            nums[idx] = kNum;
        }

        maxKinds--;

        Set<Integer> set = new HashSet<>();
        set.add(kNum);

        //填充出现M次的其他数
        while (maxKinds != 0){
            int mNum = 0;
            do {
                mNum = randomNum(range);
            }while (set.contains(mNum));
            for (int i = 0; i < m; i++) {
                nums[idx++] = mNum;
            }
            maxKinds--;
        }
        for (int i = 0; i < nums.length; i++) {
            int j = (int) (Math.random() * nums.length);
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }

    public static int randomNum(int range){
        //[1, range] - [1, range]
        return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
    }

    public static void print(int[] nums){
        System.out.println(Arrays.toString(nums));
    }
}

