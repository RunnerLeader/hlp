package com.note.tree;

import java.util.Stack;

public class PrintTree {
    public static void main(String[] args) {

    }

    //递归
    public static void beforePrint(TreeNode node){
        if (node == null){
            return;
        }
        System.out.println(node.val);
        beforePrint(node.left);
        beforePrint(node.right);
    }

    //迭代
    //头节点入栈
    //栈不为空
    //弹出 打印
    //先右后左 压入栈
    //循环
    public static void beforePrintOnRecur(TreeNode node){
        if (node == null){
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.empty()){
            TreeNode popNode = stack.pop();
            System.out.println(popNode.val);
            if (popNode.right!=null){
                stack.push(popNode.right);
            }
            if (popNode.left!=null){
                stack.push(popNode.left);
            }
        }
    }

    //两个栈
    //头节点入打印栈
    //打印栈不为空
    //弹出  入收集栈
    //先压左 后压右  到打印栈和
    //最后打印收集栈
    public static void afterPrintOnRecur(TreeNode node){
        if (node == null){
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> collectStack = new Stack<>();
        stack.push(node);
        while (!stack.empty()){
            TreeNode popNode = stack.pop();
            collectStack.push(popNode);
            System.out.println(popNode.val);
            if (popNode.left!=null){
                stack.push(popNode.left);
            }
            if (popNode.right!=null){
                stack.push(popNode.right);
            }
        }
        while (!collectStack.empty()){
            System.out.println(collectStack.pop().val);
        }
    }

    //中序遍历
    //头节点不为空 入栈 将左节点给头
    //为空 弹出打印  并  将右节点给头
    //循环

    //思路所有左节点入栈 直到为空 然后弹栈打印 并将右节点上所有左节点入栈
    public static void inPrintOnRecur(TreeNode node){
        if (node == null){
            return;
        }

        Stack<TreeNode> stack = new Stack<>();

        while (!stack.empty() || node != null){
            if (node != null){
                stack.push(node);
                node = node.left;
            }else {
                TreeNode popNode = stack.pop();
                System.out.println(popNode.val);
                node = popNode.right;
            }
        }
    }

}
