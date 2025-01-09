package com.note.linked.likou;

import com.note.linked.Node;

import java.util.HashMap;
import java.util.Stack;

public class LinkedReverse {

    public static void main(String[] args) {

    }


    //反转单链表
    public static Node reverseNode(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return curr;
    }

    //删除单链表上目标
    public static Node deleteValue(Node head, int target) {
        Node dummy = new Node(0, head);

        Node prev = dummy;
        Node curr = head;

        while (curr != null) {
            Node next = curr.next;
            if (curr.val == target) {
                prev.next = next;
            } else {
                prev = curr;
            }
            curr = next;
        }
        return dummy.next;
    }

    //回文链表 O(N) O(N) 解法
    public boolean isPalindrome1(ListNode head) {
        Stack<Integer> stack = new Stack<>();

        ListNode curr = head;
        while (curr != null) {
            stack.push(curr.val);
            curr = curr.next;
        }

        curr = head;
        while (curr != null) {
            if (curr.val != stack.pop()) {
                return false;
            }
            curr = curr.next;
        }
        return true;
    }

    //回文链表 O(N) O(1) 解法  1->2->null<-2<-1     1->2->3->null<-2<-1
    public boolean isPalindrome2(ListNode head) {
        ListNode leftEnd = head;
        ListNode temp = head;
        while (temp.next != null && temp.next.next != null) {
            leftEnd = leftEnd.next;
            temp = temp.next.next;
        }

        temp = leftEnd.next;
        leftEnd.next = null;

        ListNode rightEnd = null;
        while (temp != null) {
            ListNode node = temp.next;
            temp.next = rightEnd;
            rightEnd = temp;
            temp = node;
        }

        ListNode p1 = head;
        ListNode p2 = rightEnd;
        boolean result = true;
        while (p1 != null && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        ListNode rightStart = null;
        while (rightEnd != null) {
            ListNode node = rightEnd.next;
            rightEnd.next = rightStart;
            rightStart = rightEnd;
            rightEnd = node;
        }

        leftEnd.next = rightStart;
        return result;
    }

    //链表上 < pivot  = pivot  > pivot
    public ListNode listPartition(ListNode head, int pivot) {
        ListNode sH = null;
        ListNode sT = null;
        ListNode eH = null;
        ListNode eT = null;
        ListNode bH = null;
        ListNode bT = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            if (head.val > pivot) {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head;
                }
            } else if (head.val < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            }
            head = next;
        }
        if (sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }
        if (eT != null) {
            eT.next = bH;
        }
        return sH != null ? sH : (eH != null ? eH : bH);
    }

    //copy链表
    public static ListNode copy1(ListNode head) {
        ListNode curr = head;
        HashMap<ListNode, ListNode> map = new HashMap<>();
        while (curr != null) {
            map.put(curr, new ListNode(curr.val));
            curr = curr.next;
        }

        curr = head;
        while (curr != null) {
            ListNode curr1 = map.get(curr);
            curr1.next = map.get(curr.next);
            curr1.random = map.get(curr.random);
            curr = curr.next;
        }
        return map.get(head);
    }


    public static ListNode copy2(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            ListNode curr1 = new ListNode(curr.val);
            curr.next = curr1;
            curr1.next = next;
            curr = next;
        }

        curr = head;
        while (curr != null) {
            ListNode curr1 = curr.next;
            curr1.random = curr.random != null ? curr.random.next : null;
            curr = curr.next.next;
        }

        ListNode dummy = new ListNode();

        curr = head;
        ListNode copy = dummy;
        while (curr != null) {
            copy.next = curr.next;
            copy = curr.next;
            curr.next = curr.next.next;
            curr = curr.next.next;
        }

        return dummy.next;
    }

}