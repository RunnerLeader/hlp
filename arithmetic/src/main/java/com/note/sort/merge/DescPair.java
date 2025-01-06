package com.note.sort.merge;

import com.note.utils.ArrUtils;

/**
 * 求逆序对
 */
public class DescPair {
    public static void main(String[] args) {
        int[] nums = ArrUtils.randomArray(5, 10);
        if (nums.length == 0){
            return;
        }
        ArrUtils.print(nums);
        int cnt = descPair(nums, 0, nums.length-1);
        System.out.println(cnt);
        ArrUtils.print(nums);
    }

    public static int descPair(int[] nums, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + (r - l) / 2;
        return descPair(nums, l, mid) + descPair(nums, mid + 1, r) + merge(nums, l, mid, r);
    }

    public static int merge(int[] nums, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];

        int p1 = l;
        int p2 = mid + 1;
        int i = 0;
        int cnt = 0;

        while (p1 <= mid && p2 <= r) {
            if (nums[p1] > nums[p2]) {
                cnt += (mid - p1 + 1);
                temp[i++] = nums[p2++];
            } else {
                temp[i++] = nums[p1++];
            }
        }

        while (p1 <= mid) {
            temp[i++] = nums[p1++];
        }
        while (p2 <= r) {
            temp[i++] = nums[p2++];
        }

        for (i = 0; i < temp.length; i++) {
            nums[l+i] = temp[i];
        }
        return cnt;
    }


}
