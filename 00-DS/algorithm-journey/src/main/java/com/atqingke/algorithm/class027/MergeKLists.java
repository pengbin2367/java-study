package com.atqingke.algorithm.class027;

import java.util.ArrayList;
import java.util.PriorityQueue;

// https://www.nowcoder.com/practice/65cfde9e5b9b4cf2b6bafa5f3ef33fa6
public class MergeKLists {

    class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode mergeKLists (ArrayList<ListNode> lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode node : lists) {
            if (node != null) {
                heap.add(node);
            }
        }
        ListNode head = heap.poll();
        if (head == null) return head;
        ListNode pre = head;
        if (pre.next != null) heap.add(pre.next);
        while (!heap.isEmpty()) {
            ListNode cur = heap.poll();
            pre.next = cur;
            pre = cur;
            if (cur.next != null) heap.add(cur.next);
        }
        return head;
    }
}
