package com.note.search;

import com.note.utils.ArrUtils;

/**
 * 力扣
 * 34 704 69 35
 * 287 鸽巢原理 (快慢指针解法)
 * 611
 *
 *
 *
 */
public class HalfSearch {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 6, 6, 8, 8, 10};
        ArrUtils.print(nums);
        System.out.println(findFirstPos(nums, 7));
        System.out.println(findLastPos(nums, 7));
    }

    //有序数组中查找目标值
    public static int findTarget(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int l = 0;
        int r = nums.length - 1;
        int rtIdx = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                rtIdx = mid;
                break;
            } else if (nums[mid] > target) {
                //大了 mid以及mid之后的所有数都不可能是答案  缩小范围[l, mid - 1]
                r = mid - 1;
            } else {
                //小了 mid以及mid之前的所有数都不可能是答案  缩小范围[mid + 1, r]
                l = mid + 1;
            }
        }
        return rtIdx;
    }

    //有序数组中  查找大于等于目标值第一次出现的下标
    public static int findFirstPos(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] >= target) {
                //mid之后的肯定不是第一次出现的 mid有可能是第一次出现的 缩小范围[l, mid]
                r = mid;
            } else {
                //反之 缩小范围[mid + 1, r]
                l = mid + 1;
            }
        }
        //有可能不存在,再确认一下
        if (nums[l] >= target) {
            return l;
        }
        return -1;
    }

    //有序数组中  查找等于目标值最后一次出现的下标
    public static int findLastPos(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (nums[mid] >= target){
                //<mid位置上的数肯定不是最后一次 mid位置上的可能是最后一次的 缩小范围[mid, r]
                l = mid;
            }else {
                //反之 缩小范围[l, mid-1]
                r = mid - 1;
            }
        }
        //不一定存在, 最后再判断一下
        if (nums[l] == target){
            return l;
        }
        return -1;
    }

    //无序数组中 查找局部中最小
    public static int findPartMin(int[] nums, int target){
        //todo hlp
        return 0;
    }

}