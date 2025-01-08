package com.note.linkedstack;

public class LinkedReverse {

    public static void main(String[] args) {

    }


    //反转单链表
    public static Node reverseNode(Node head){
        Node prev = null;
        Node curr = head;
        while (curr != null){
            Node next = curr.next;
            curr.next = prev;
            prev  = curr;
            curr = next;
        }
        return curr;
    }

    //删除单链表上目标
    public static Node deleteValue(Node head, int target){
        Node dummy = new Node(0, head);

        Node prev = dummy;
        Node curr = head;

        while (curr!=null){
            Node next = curr.next;
            if (curr.val == target) {
                prev.next = next;
            }else {
                prev = curr;
            }
            curr = next;
        }
        return dummy.next;
    }



}