package com.note.sort;

import com.note.utils.ArrUtils;

public class SelectSort {
    public static void main(String[] args) {
        int[] nums = ArrUtils.randomArray(10, 10);
        ArrUtils.print(nums);
        test(nums);
        ArrUtils.print(nums);
    }

    public static void sort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                minIdx = nums[j] < nums[minIdx] ? j : minIdx;
            }
            if (minIdx != i) {
                ArrUtils.swap(nums, minIdx, i);
            }
        }
    }

    //note: 选择排序
    //插入排序是往[无序方向]进行 查看->比较  最后交换
    //如  [1,2,4,2,6,5,7]
    // 假设当前遍历到i=0位置
    // 那么这一轮就是将下标为0位置的1  与下标[1,6]范围内所有数进行比较
    // 得到这一轮最小值的下标minIdx
    // 将 i与minIdx位置交换
    // 此轮排序结束 i=0位置的数排好序
    // 进入下一轮 开始排i=1位置的数

    //总结
    // 综上可以看出 插入排序时间复杂度为 O(n²)  空间复杂度O(1)
    // 且该算法 不受数据状况影响 每一轮都会与无序方向上的所有数比较

    public static void test(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

    }
}
