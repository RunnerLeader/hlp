package com.note.linkedstack;

public class DoubleEndQueue<T> {
    private LinkedNode<T> tail;
    private LinkedNode<T> head;

    public void addFromHead(T val) {
        LinkedNode<T> node = new LinkedNode<>(val);
        if (head == null) {
            tail = node;
            head = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    public void addFromTail(T val) {
        LinkedNode<T> node = new LinkedNode<>(val);
        if (head == null) {
            tail = node;
            head = node;
        } else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
    }

    public T popFromHead(){
        if (head == null){
            return null;
        }
        LinkedNode<T> node = head;
        if (head == tail){
            head = null;
            tail = null;
        }else {
            head = head.next;
            node.next = null;
            head.prev = null;
        }
        return node.val;
    }

    public T popFromTail(){
        if (head == null){
            return null;
        }
        LinkedNode<T> node = tail;
        if (head == tail){
            head = null;
            tail = null;
        }else {
            tail = tail.prev;
            node.prev = null;
            tail.next = null;
        }
        return node.val;
    }

    class LinkedNode<T> {
        T val;
        LinkedNode<T> next;
        LinkedNode<T> prev;

        public LinkedNode() {
        }

        public LinkedNode(T val) {
            this.val = val;
        }
    }
}
