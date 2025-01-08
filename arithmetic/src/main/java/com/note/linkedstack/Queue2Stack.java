package com.note.linkedstack;

import java.util.LinkedList;
import java.util.Queue;

public class Queue2Stack {
    private Queue<Integer> queue;
    private Queue<Integer> tempQueue;

    public Queue2Stack() {
        queue = new LinkedList<>();
        tempQueue = new LinkedList<>();
    }


    public void offer(int val){
        queue.offer(val);
    }

    public int poll(){
        if (queue.isEmpty()){
            throw new RuntimeException("queue is empty");
        }
        while (queue.size() > 1){
            tempQueue.offer(queue.poll());
        }
        int val = queue.poll();
        Queue<Integer> temp = queue;
        queue = tempQueue;
        tempQueue = temp;
        return val;
    }

    public int peek(){
        if (queue.isEmpty()){
            throw new RuntimeException("queue is empty");
        }
        while (queue.size() > 1){
            tempQueue.offer(queue.poll());
        }
        int val = queue.poll();
        tempQueue.offer(val);
        Queue<Integer> temp = queue;
        queue = tempQueue;
        tempQueue = temp;
        return val;
    }


}
