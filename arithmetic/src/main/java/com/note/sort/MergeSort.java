package com.note.sort;

import com.note.utils.ArrUtils;

/**
 * 归并排序  时间复杂度 O(NlogN)
 * 力扣 51 315 493 50
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] nums = ArrUtils.randomArray(10, 10);
        ArrUtils.print(nums);
        mergeSort(nums, 0, nums.length - 1);
        ArrUtils.print(nums);
    }

    //求逆序对
    public static void mergeSort(int[] nums, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + (r - l) / 2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        merge(nums, l, mid, r);
    }

    public static void merge(int[] nums, int l, int mid, int r) {
        if (nums == null || nums.length == 0){
            return;
        }
        int[] temp = new int[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int i = 0;
        while (p1 <= mid && p2 <= r) {
            temp[i++] = nums[p1] <= nums[p2] ? nums[p1++] : nums[p2++];
        }
        while (p1 <= mid) {
            temp[i++] = nums[p1++];
        }
        while (p2 <= r) {
            temp[i++] = nums[p2++];
        }

        for (i = 0; i < temp.length; i++) {
            nums[l + i] = temp[i];
        }
    }

}
