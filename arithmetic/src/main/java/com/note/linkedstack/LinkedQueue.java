package com.note.linkedstack;

/**
 * 链表实现队列
 */
public class LinkedQueue<T> {


    public static void main(String[] args) {

    }

    private DoubleEndQueue<T> queue;

    public LinkedQueue() {
    }

    public LinkedQueue(DoubleEndQueue<T> queue) {
        this.queue = queue;
    }

    public void offer(T val){
        queue.addFromTail(val);
    }

    public void poll(){
        queue.popFromHead();
    }
}
