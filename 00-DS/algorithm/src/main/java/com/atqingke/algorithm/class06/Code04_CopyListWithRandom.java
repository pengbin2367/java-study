package com.atqingke.algorithm.class06;

import java.util.HashMap;
import java.util.Map;

public class Code04_CopyListWithRandom {

    /**
         一种特殊的单链表节点类描述如下
         class Node {
            int value;
            Node next;
            Node rand;
            Node(int value) { this.value = value }
         }
         rand指针是单链表节点结构中新增的指针，可能指向链表中任意一个节点，也可能指向null
         给定一个由Node节点类型组成的无环单链表的头节点head,请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点
         【要求】时间复杂度O(N),空间复杂度O(1)
     */
    public static class Node {
        private int value;
        private Node next;
        private Node rand;
        public Node(int value) {
            this.value = value;
        }
    }

    public static Node copyListWithRand1(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static Node copyListWithRand2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        // set copy node next
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        // set copy node rand
        cur = head;
        Node curCopy = null;
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        Node res = head.next;
        cur = head;
        // split
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }
}
