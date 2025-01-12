package com.note.tree;

import java.util.Stack;

/**
 * 搜索二叉树
 * 每一颗子树  左树都比他小 右树都比他大
 * 中序遍历都是递减的
 */
public class BSTree {

    public static void main(String[] args) {

    }

    //递归法
    private int prevVal = Integer.MIN_VALUE;

    public boolean isBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean leftBST = isBST1(root.left);
        if (!leftBST) {
            return false;
        }
        if (root.val <= prevVal) {
            return false;
        } else {
            prevVal = root.val;
        }
        return isBST1(root.right);
    }

    //迭代法
    public boolean isBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        int prevVal = Integer.MIN_VALUE;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (!stack.empty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.pop();
                if (node.val <= prevVal) {
                    return false;
                } else {
                    prevVal = node.val;
                }
                root = node.right;
            }
        }
        return true;
    }

    //dp 套路解法
    // 左树时 搜索树
    // 右树时 搜索树
    // 左树最大值 < 我
    // 右树最小值 > 我
    public boolean isBST3(TreeNode root) {
        return process(root).isBST;
    }

    public ReturnType process(TreeNode root) {
        if (root == null) {
            return null;
        }

        ReturnType leftReturn = process(root.left);
        ReturnType rightReturn = process(root.right);

        int max = root.val;
        int min = root.val;
        if (leftReturn != null){
            max = Math.max(leftReturn.max, max);
            min = Math.max(leftReturn.min, min);
        }
        if (rightReturn != null){
            max = Math.max(rightReturn.max, max);
            min = Math.max(rightReturn.min, min);
        }

//        boolean isBST = true;
//        if (leftReturn != null && (!leftReturn.isBST || leftReturn.max >= root.val)){
//            isBST = false;
//        }
//        if (rightReturn != null && (!rightReturn.isBST || rightReturn.min <= root.val)){
//            isBST = false;
//        }

        boolean isBST = false;
        if ((leftReturn == null? true : (leftReturn.isBST && root.val > leftReturn.max))
            && (rightReturn == null? true : (rightReturn.isBST && root.val < rightReturn.min))){
            isBST = true;
        }

        return new ReturnType(isBST, max, min);
    }


    class ReturnType {
        private boolean isBST;
        private int max;
        private int min;

        public ReturnType(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }
}
