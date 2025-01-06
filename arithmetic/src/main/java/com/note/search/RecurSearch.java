package com.note.search;

import com.note.utils.ArrUtils;

/**
 * 递归
 * 时间复杂度计算
 * master公式
 * T(N) = a*T(N/b) + O(N**d)
 * T(N) 目问题的数据量
 * a*T(N/b)  子问题的规模都是N/b的规模  a代表子问题调用次数
 * O(N**d)   除了子问题之外剩下的动作时间复杂度
 * 递归求做大值   T(N) = 2*T(N/2) + O(1)
 *
 *
 */
public class RecurSearch {
    public static void main(String[] args) {
        int[] nums = ArrUtils.randomArray(10, 10);
        ArrUtils.print(nums);
        System.out.println(process(nums, 0, nums.length - 1));
    }

    //递归找最大值
    public static int process(int[] nums, int l, int r) {
        if (l == r) {
            return nums[l];
        }
        int mid = l + (r - l) / 2;
        int left = process(nums, l, mid);
        int right = process(nums, mid + 1, r);
        return Math.max(left, right);
    }


    //求逆序对
    public static int mergeSort(int[] nums, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + (r - l) / 2;
        int left = mergeSort(nums, l, mid);
        int right = mergeSort(nums, mid + 1, r);
        int merged = merge(nums, l, mid, r);
        return left + right + merged;
    }

    public static int merge(int[] nums, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        int left = l;
        int right = mid + 1;

        int i = 0;
        int cnt = 0;
        while (left <= mid && right <= r) {
            if (nums[left] <= nums[right]) {
                temp[i++] = nums[left++];
            } else {
                cnt += (mid - left + 1);
                temp[i++] = nums[right++];
            }
        }
        while (left <= mid){
            temp[i++] = nums[left++];
        }
        while (right <= r){
            temp[i++] = nums[right++];
        }

        for (i = 0; i < temp.length; i++){
            nums[l+i] = temp[i];
        }

        return cnt;
    }
}
