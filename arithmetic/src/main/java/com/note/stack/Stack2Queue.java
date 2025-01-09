package com.note.stack;

import java.util.Stack;

public class Stack2Queue {
    private Stack<Integer> pushStack;
    private Stack<Integer> popStack;

    public void offer(int val) {
        push2pop();
        pushStack.push(val);
    }

    public int poll() {
        if (empty()) {
            throw new RuntimeException("queue is empty");
        }
        push2pop();
        return popStack.pop();
    }

    public int peek(){
        push2pop();
        return popStack.peek();
    }

    private void push2pop() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                pushStack.push(pushStack.pop());
            }
        }
    }

    public boolean empty() {
        return pushStack.isEmpty() && popStack.isEmpty();
    }


}
