package com.note.tree;

import java.util.Stack;

public class PrintTree {
    public static void main(String[] args) {

    }

    public static void beforePrint(TreeNode node){
        if (node == null){
            return;
        }
        System.out.println(node.val);
        beforePrint(node.left);
        beforePrint(node.right);
    }

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


}
