package com.note.tree;

public class FullTree {

    public static void main(String[] args) {

    }

    public boolean isF(TreeNode root) {
        if (root == null){
            return true;
        }
        ReturnData data = f(root);
        return data.nodes == (1 << data.deep - 1);
    }

    public ReturnData f(TreeNode root) {
        if (root == null) {
            new ReturnData(0, 0);
        }
        ReturnData leftData = f(root.left);
        ReturnData rightData = f(root.right);
        return new ReturnData(Math.max(leftData.deep, rightData.deep) + 1,
                leftData.nodes + rightData.nodes + 1);
    }

    class ReturnData {
        int deep;
        int nodes;

        public ReturnData(int deep, int nodes) {
            this.deep = deep;
            this.nodes = nodes;
        }
    }
}
