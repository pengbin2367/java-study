package com.atqingke.algorithm.class018;

import java.util.Stack;

public class BinaryTreeTraversalIteration {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    private void preOrder(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                root = stack.pop();
                System.out.print(root.val + " ");
                if (root.right != null) stack.push(root.right);
                if (root.left != null) stack.push(root.left);
            }
            System.out.println();
        }
    }

    private void inOrder(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    System.out.print(root.val + " ");
                    root = root.right;
                }
            }
            System.out.println();
        }
    }

    private void postOrder(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            Stack<TreeNode> collect = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                root = stack.pop();
                collect.push(root);
                if (root.left != null) stack.push(root.left);
                if (root.right != null) stack.push(root.right);
            }
            while (!collect.isEmpty()) {
                System.out.print(collect.pop().val + " ");
            }
            System.out.println();
        }
    }

    private void postOrderIter(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode cur = stack.peek();
                if (cur.left != null && cur.left != root.left && cur.right != root.right) {
                    stack.push(cur.left);
                } else if (cur.right != null && cur.right != root.right) {
                    stack.push(cur.right);
                } else {
                    System.out.println(cur.val + " ");
                    root = stack.pop();
                }
            }
            System.out.println();
        }
    }
}
