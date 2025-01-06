package com.note.sort;

import com.note.utils.ArrUtils;

public class InsertSort {
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
        for (int i = 1; i < n; i++) {
            for (int j = i-1; j>=0 && nums[j] > nums[j+1]; j--){
                ArrUtils.swap(nums, j, j+1);
            }
        }
    }

    //note: 插入排序
    //插入排序是往[有序方向]进行 查看->比较->交换
    //如  [1,2,4,2,6,5,7]
    // 假设当前遍历到i=3位置
    // 那么这一轮就是将2 插入到[1,2,4]中
    // 将2 与 4比较  4大 进行交换   [1,2,2,4]
    // 将2 与 2比较  相等 不动      [1,2,2,4,6,5,7]
    // 此轮排序结束 i=3 位置的数被插入到正确位置  [0,3] 范围就被排好序了
    // 进入下一轮 开始排i=4位置的数

    //总结
    // 综上可以看出 插入排序时间复杂度为 O(n²)  空间复杂度O(1)
    // 且该算法 比较数据状况影响 最好情况下时间复杂度会进化到O(n)

}
