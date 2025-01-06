package com.note.sort.merge;

import com.note.utils.ArrUtils;

public class SmallSum {
    public static void main(String[] args) {
        int[] nums = ArrUtils.randomArray(5, 10);
        if (nums.length == 0){
            return;
        }
        ArrUtils.print(nums);
        int sum = smallSum(nums, 0, nums.length - 1);
        ArrUtils.print(nums);
        System.out.println(sum);
    }

    public static int smallSum(int[] nums, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + (r - l) / 2;
        return smallSum(nums, l, mid) + smallSum(nums, mid + 1, r) + merge(nums, l, mid, r);
    }

    public static int merge(int[] nums, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int i = 0;
        int cnt = 0;

        while (p1 <= mid && p2 <= r) {
            if (nums[p1] < nums[p2]) {
                cnt += nums[p1] * (r - p2 + 1);
                temp[i++] = nums[p1++];
            }else {
                temp[i++] = nums[p2++];
            }
        }

        while (p1<=mid){
            temp[i++] = nums[p1++];
        }
        while (p2<=r){
            temp[i++] = nums[p2++];
        }

        for (i=0; i<temp.length;i++){
            nums[l+i] = temp[i];
        }
        return cnt;
    }

}
