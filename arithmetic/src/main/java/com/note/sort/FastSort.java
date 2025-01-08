package com.note.sort;

import com.note.utils.ArrUtils;

import java.util.Arrays;

/**
 * 快排
 * 随机快排  时间复杂度O(NlogN) 空间复杂度 O(N)
 *
 *
 */
public class FastSort {
    public static void main(String[] args) {
//        int maxLen = 100;
//        int range = 200;
//        int testTime = 10000;
//        System.out.println("开始测试");
//        for (int i = 0; i < testTime; i++) {
//            int[] nums = ArrUtils.randomArray(maxLen, range);
//            int[] nums1 = ArrUtils.copyArray(nums);
//            Arrays.sort(nums1);
//            quickSort2(nums, 0, nums.length - 1);
//            if (!ArrUtils.isEquals(nums , nums1)) {
//                System.out.println("出错了!");
//            }
//        }
//        System.out.println("结束测试");
        int[] nums = {5, 1, 1, 2, 0, 0};
        quickSort2(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    public static void quickSort1(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int partition = partition1(nums, l, r);
        quickSort1(nums, l, partition - 1);
        quickSort1(nums, partition + 1, r);
    }

    //nums[l,r]上
    //<=x  放在左边
    //>x  放在右边
    public static int partition1(int[] nums, int l, int r) {
        int less = l - 1;
        int idx = l;
        while (idx < r) {
            if (nums[idx] <= nums[r]) {
                ArrUtils.swap(nums, idx, ++less);
            }
            idx++;
        }
        ArrUtils.swap(nums, ++less, r);
        return less;
    }


    public static void quickSort2(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] partition = partition2(nums, l, r);
        quickSort2(nums, l, partition[0] - 1);
        quickSort2(nums, partition[1] + 1, r);
    }

    //nums[l,r]上
    //<x  放在左边
    //=x  放中间
    //>x  放在右边
    public static int[] partition2(int[] nums, int l, int r) {
        int base = (int) (Math.random() * (r - l + 1)) + l;
        ArrUtils.swap(nums, r, base);
        int less = l - 1;
        int more = r;
        int idx = l;
        while (idx < more) {
            if (nums[idx] == nums[r]) {
                idx++;
            } else if (nums[idx] > nums[r]) {
                ArrUtils.swap(nums, idx, --more);
            } else {
                ArrUtils.swap(nums, idx++, ++less);
            }
        }
        ArrUtils.swap(nums, more, r);
        return new int[]{less + 1, more};
    }


}
