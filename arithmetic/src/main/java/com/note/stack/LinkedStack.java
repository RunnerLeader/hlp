package com.note.stack;

import com.note.linked.DoubleEndQueue;

/**
 * 链表实现栈
 */
public class LinkedStack<T> {


    public static void main(String[] args) {

    }

    private DoubleEndQueue<T> queue;

    public LinkedStack() {
    }

    public LinkedStack(DoubleEndQueue<T> queue) {
        this.queue = queue;
    }

    public void push(T val){
        queue.addFromTail(val);
    }

    public void poll(){
        queue.popFromTail();
    }
}
