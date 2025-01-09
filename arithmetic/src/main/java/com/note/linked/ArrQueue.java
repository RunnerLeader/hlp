package com.note.linked;

/**
 * 数组实现队列
 */
public class ArrQueue {
    public static void main(String[] args) {
        ArrQueue queue = new ArrQueue(5);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

    private int[] arr;
    private final int limit;
    private int size;
    private int offeri;
    private int polli;

    public ArrQueue(int limit) {
        arr = new int[limit];
        this.limit = limit;
        size = 0;
        offeri = 0;
        polli = 0;
    }

    public void push(int val) {
        if (size == limit) {
            throw new RuntimeException("queue is full");
        }
        arr[offeri] = val;
        size++;
        this.offeri = nextIdx(offeri);
    }

    public int poll(){
        if (size == 0){
            throw new RuntimeException("queue is empty");
        }
        int val = arr[polli];
        size--;
        this.polli = nextIdx(polli);
        return val;
    }

    public int nextIdx(int i) {
        return i < limit - 1 ? i + 1 : 0;
    }
}
