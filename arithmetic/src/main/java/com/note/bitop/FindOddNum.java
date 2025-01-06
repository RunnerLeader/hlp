package com.note.bitop;

public class FindOddNum {
    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3,3,4,4,4};
        System.out.println(findOddTimesNum(nums));
        System.out.println(extractLastOne(3));
        findOddTimesNums(nums);
    }

    //一个数组中 一种数出现奇数次 另一种数出现偶数次 找到出现奇数次数的数
    public static int findOddTimesNum(int[] nums){
        if (nums == null || nums.length == 0){
            return -1;
        }
        int eor = 0;
        for (int num : nums){
            eor ^=num;
        }
        return eor;
    }

    //一个数组中有2种数出现奇数次 其他数都出现了偶数次 找到这两种数
    public static void findOddTimesNums(int[] nums){
        if (nums == null || nums.length == 0){
            return;
        }

        int eor = 0; // a^b
        for (int num : nums){
            eor ^= num;
        }

        int lastOneNum = eor & -eor;
        int one = 0;
        for (int num : nums){
            if ((lastOneNum & num) == 1){
                one ^=num;
            }
        }
        int two = eor ^ one;
        System.out.println(one + "," + two);
    }


    //提取一个数最右侧的一
    public static int extractLastOne(int num){
        //return num &(~num +1);
        return num & -num;
    }
}
