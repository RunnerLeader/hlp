package com.note.sort;

import com.note.utils.ArrUtils;

public class BubbleSort {

    public static void main(String[] args) {
        int[] nums = ArrUtils.randomArray(10, 10);
        ArrUtils.print(nums);
        sort(nums);
        ArrUtils.print(nums);
    }

    public static void sort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int n = nums.length;
        for (int e = n - 1; e >= 0; e--) {
            for (int j = 0; j < e; j++) {
                if (nums[j] > nums[j + 1]) {
                    ArrUtils.swap(nums, j, j + 1);
                }
            }
        }
    }

    //note: 插入排序
    //插入排序是往[有序方向]进行 查看->比较->交换
    //如  [1,2,4,2,6,5,7]
    // 假设当前遍历到i=0位置
    // 那么这一轮会从下标0开始往后两两比较 大的放后边 小的放前边
    // 第一轮
    // 第一次   比较 1,2  不交换
    // 第二次   比较 2,4  不交换
    // 第三次   比较 4,2  交换 当前顺序[1,2,2,4,6,5,7]
    // 第四次   比较 4,6  不交换
    // 第五次   比较 6,5  交换 当前顺序[1,2,2,4,5,6,7]
    // 第六次   比较 6,7  不交换
    // 第一轮结束 i=6的位置排好序
    // 进入下一轮 开始从i=1位置开始往后->比较->交换

    //总结
    // 综上可以看出 插入排序时间复杂度为 O(n²)  空间复杂度O(1)
    // 且该算法 不受数据状况影响 每一轮都会将无序方向上的所有数两两比较

}
