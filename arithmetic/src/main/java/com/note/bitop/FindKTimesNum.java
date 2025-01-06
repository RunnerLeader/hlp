package com.note.bitop;

import com.note.utils.ArrUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 一个数组中由一种数出现K次 其他数出现M次 M>1 K<M
 * 找到 出现K次的数
 * 要求 额外空间复杂度O(1) 时间复杂读O(N)
 */
public class FindKTimesNum {
    public static void main(String[] args) {
        int maxKinds = 10;
        int range = 200;
        int testTime = 10000;
        int max = 9;
        System.out.println("开始测试");
        for (int i = 0; i < testTime; i++) {
            int a = (int) (Math.random() * max) + 1; //[1,9]
            int b = (int) (Math.random() * max) + 1; //[1,9]
            int k = Math.min(a, b); // 出现k次
            int m = Math.max(a, b); // 出现m次
            if (k == m) {
                m++;
            }
            int[] arr = ArrUtils.randomArr(maxKinds, range, k, m);
            int ans1 = hashFind(arr, k);
            int ans2 = findKTimesNum(arr, k, m);
            if (ans2 != ans1) {
                System.out.println("出错了!");
            }
        }
        System.out.println("结束测试");
    }

    public static int findKTimesNum(int[] nums, int k, int m) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int[] arr = new int[32];
        for (int num : nums) {
            //拿到num每一位上的数字 赋值给arr
            for (int i = 0; i < 32; i++) {
                arr[i] += ((num >> i) & 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (arr[i] % m == k) {
                ans |= (1 << i);
            }
        }
        return ans;
    }


    public static int hashFind(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : map.keySet()) {
            if (map.get(num) == k) {
                return num;
            }
        }
        return -1;
    }
}