package com.note.linkedstack;

/**
 * 数组实现栈
 */
public class ArrStack {
    public static void main(String[] args) {
        ArrStack stack = new ArrStack(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    private int[] arr;
    private int size;
    private int limit;
    private int nexti;

    public ArrStack(int limit){
        arr = new int[limit];
        this.limit = limit;
        size = 0;
        nexti = 0;
    }

    public void push(int val){
        if (size == limit){
            throw new RuntimeException("stack is full");
        }
        arr[nexti++] = val;
        size++;
    }

    public int pop(){
        if (size == 0){
            throw new RuntimeException("stack is empty");
        }
        int val = arr[--nexti];
        size--;
        return val;
    }

}