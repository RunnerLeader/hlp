package com.note;

public class Test {
    public static void main(String[] args) {
        System.out.println(1 | 2);
    }


    public static void bitFind(){
        int[] nums = {1, 1, 2, 3, 4, 3, 4, 4, 5, 5};
        int eor = 0;
        for (int num : nums) {
            eor ^= num;
        }
        //找出最后一位1 ^是无进位相加
        int eor1 = eor & (~eor + 1);
        int one = 0;
        for (int num : nums) {
            if ((eor1 & num) == 0) {
                one ^= num;
            }
        }
        int two = eor ^ one;
        System.out.println("one = " + one + ", two=" + two);
    }
}
