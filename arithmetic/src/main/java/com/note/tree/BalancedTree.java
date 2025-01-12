package com.note.tree;

/**
 * 平衡二叉树
 * 左右树都是平衡二叉树 且 树高 < 2
 *
 */
public class BalancedTree {

    public boolean isBalanceTree(TreeNode root){
        return isBalance(root).isBalance;
    }

    public ReturnType isBalance(TreeNode node){
        if (node == null){
            return new ReturnType(true, 0);
        }
        ReturnType leftBalance = isBalance(node.left);
        ReturnType rightBalance = isBalance(node.right);
        int deep = Math.max(leftBalance.height, rightBalance.height) + 1;
        return new ReturnType(leftBalance.isBalance && rightBalance.isBalance &&
                Math.abs(leftBalance.height - rightBalance.height) < 2, deep);
    }

    class ReturnType{
        private boolean isBalance;
        private int height;

        public ReturnType(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }
}
