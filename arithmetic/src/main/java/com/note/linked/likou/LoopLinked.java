package com.note.linked.likou;

public class LoopLinked {

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null){
            return null;
        }
        ListNode loop1 = loopNode(headA);
        ListNode loop2 = loopNode(headB);
        if (loop1 == null && loop2 ==null){
            return noLoopNode(headA, headB);
        }
        if (loop1!=null && loop2 != null){
            return bothLoopNode(headA, loop1, headB, loop2);
        }
        return null;
    }

    //找入环节点
    public static ListNode loopNode(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        ListNode s = head.next;
        ListNode f = head.next.next;
        while (s != f) {
            if (f.next == null || f.next.next == null) {
                return null;
            }
            s = s.next;
            f = f.next.next;
        }

        f = head;
        while (s != f) {
            s = s.next;
            f = f.next;
        }

        return s;
    }

    //无环相交
    public static ListNode noLoopNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;

        ListNode aEnd = null;
        int lenA = 0;
        while(a!=null){
            lenA++;
            aEnd = a;
            a=a.next;
        }

        ListNode bEnd = null;
        int lenB = 0;
        while(b!=null){
            lenB++;
            bEnd = b;
            b=b.next;
        }

        if(aEnd!=bEnd){
            return null;
        }

        aEnd = headB;
        bEnd = headA;

        int redu = Math.abs(lenA - lenB);
        if(lenA - lenB > 0){
            //a长
            while(redu > 0){
                bEnd = bEnd.next;
                redu--;
            }
        }else{
            //b长
            while(redu > 0){
                aEnd = aEnd.next;
                redu--;
            }
        }

        while(aEnd != bEnd){
            aEnd = aEnd.next;
            bEnd = bEnd.next;
        }

        return aEnd;
    }

    //有环相交
    public static ListNode bothLoopNode(ListNode head1, ListNode loop1, ListNode head2, ListNode loop2) {
        if (loop1 == loop2) {
            //有环 且如环节点是一个
            ListNode a = head1;
            ListNode b = head2;
            int n = 0;
            while (a != loop1) {
                a = a.next;
                n++;
            }
            while (b != loop2) {
                b = b.next;
                n--;
            }

            a = head2;
            b = head1;
            if (n > 0) {
                //head1长
                n = Math.abs(n);
                while (n > 0) {
                    b = b.next;
                    n--;
                }
            } else {
                //head2长
                n = Math.abs(n);
                while (n > 0) {
                    a = a.next;
                    n--;
                }
            }

            while (a != b) {
                a = a.next;
                b = b.next;
            }
            return a;
        }else {
            //入环节点不是一个, 有2种情况
            //相交 成环  两个入环节点都是相交节点
            ListNode curr = loop1.next;
            while (curr != loop1){
                if (curr == loop2){
                    return loop1;
                }
                curr = curr.next;
            }
            //各自成环 不相交
            return null;
        }
    }
}
