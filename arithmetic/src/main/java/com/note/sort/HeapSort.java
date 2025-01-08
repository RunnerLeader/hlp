package com.note.sort;

import com.note.utils.ArrUtils;

/**
 *
 * 堆排序时O(NlogN)  空间复杂度 O(1)
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] nums = ArrUtils.randomArray(10, 10);
        ArrUtils.print(nums);
        heapSort(nums);
        ArrUtils.print(nums);
    }

    public static void heapSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        //上浮
        for (int i = 0; i < nums.length; i++) { //O(N)
            heapInsert(nums, i); //O(logN)
        }
        //下沉
        int heapSize = nums.length;
        ArrUtils.swap(nums, 0, --heapSize);
        while (heapSize > 0){//O(N)
            heapify(nums, 0, heapSize);//O(log(N))
            ArrUtils.swap(nums, 0, --heapSize);//O(1)
        }
    }

    /**
     * 上浮
     *
     * @param nums 原始数组
     * @param idx  从哪个位置开始上浮
     */
    public static void heapInsert(int[] nums, int idx) {
        while (nums[idx] > nums[(idx - 1) / 2]) {
            ArrUtils.swap(nums, idx, (idx - 1) / 2);
            idx = (idx - 1) / 2;
        }
    }

    /**
     * 堆化(下沉)
     *
     * @param nums     原始数组
     * @param idx      从哪个位置开始树化(下沉)
     * @param heapSize 堆中元素数
     */
    public static void heapify(int[] nums, int idx, int heapSize) {
        int left = 2 * idx + 1;
        while (left < heapSize) {
            //左右孩子最大值
            int greIdx = left + 1 < heapSize && nums[left] < nums[left + 1] ? left + 1 : left;
            greIdx = nums[greIdx] > nums[idx] ? greIdx : idx;
            if (greIdx == idx) {
                break;
            }
            ArrUtils.swap(nums, idx, greIdx);
            idx = greIdx;
            left = 2 * idx + 1;
        }
    }
}
