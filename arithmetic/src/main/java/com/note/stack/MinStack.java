package com.note.stack;

import java.util.Stack;

/**
 * 数组实现栈
 * 提供 push pop getMin  O(1)操作
 */
public class MinStack {
    public static void main(String[] args) {

    }

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty()) {
            minStack.push(val);
        } else {
            Integer oldMin = minStack.peek();
            minStack.push(oldMin < val ? oldMin : val);
        }
    }

    public Integer getMin(){
        if (minStack.isEmpty()){
            return null;
        }
        return minStack.peek();
    }

    public Integer pop(){
        if (stack.isEmpty()) {
            return null;
        }
        minStack.pop();
        return stack.pop();
    }
}