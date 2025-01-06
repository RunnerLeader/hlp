package com.note.search;

import com.note.utils.ArrUtils;

/**
 * 力扣
 * 34 704 69 35
 * 287 鸽巢原理 (快慢指针解法)
 * 611
 */
public class HalfSearch {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 6, 6, 8, 8, 10};
        ArrUtils.print(nums);
        System.out.println(findGrTargetFirstPos(nums, 7));
        System.out.println(findEqTargetLastPos(nums, 7));
        System.out.println(findSmTargetLastPos(nums, 8));
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

    //有序数组中  查找大于等于目标值第一次出现的下标(最左侧位置)
    public static int findGrTargetFirstPos(int[] nums, int target) {
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

    //有序数组中  查找等于目标值最后一次出现的下标(最左侧位置)
    public static int findEqTargetLastPos(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (nums[mid] >= target) {
                //<mid位置上的数肯定不是最后一次 mid位置上的可能是最后一次的 缩小范围[mid, r]
                l = mid;
            } else {
                //反之 缩小范围[l, mid-1]
                r = mid - 1;
            }
        }
        //不一定存在, 最后再判断一下
        if (nums[l] == target) {
            return l;
        }
        return -1;
    }

    //有序数组中  查找小于等于目标值最后一次出现的下标 (最右侧位置)
    public static int findSmTargetLastPos(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (nums[mid] <= target) {
                //<mid的位置肯定不是最右侧的 mid 可能是最右侧出现的 缩小范围[mid, r]
                l = mid;
            } else {
                //反之缩小范围[l,mid-1]
                r = mid - 1;
            }
        }
        //不一定存在 在判断一把
        if (nums[l] <= target) {
            return l;
        }
        return -1;
    }

    //无序数组中 查找局部中最小
    public static int findPartMin(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1 || nums[0] < nums[1]) {
            return 0;
        }
        if (nums[nums.length - 1] < nums[nums.length - 1]) {
            return nums[nums.length - 1];
        }
        int l = 1;
        int r = nums.length - 2;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid-1]){
                //缩小范围[l, mid-1]
                r = mid -1;
            }else if (nums[mid] >nums[mid+1]){
                //缩小范围[mid+1, r]
                l = mid +1;
            }else {
                return mid;
            }
        }
        return 0;
    }

}