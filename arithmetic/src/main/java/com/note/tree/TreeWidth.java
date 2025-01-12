package com.note.tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeWidth {
    public static void main(String[] args) {

    }

    public int treeWidth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int width = 0;
        int size;
        while (!queue.isEmpty()) {
            size = queue.size();
            width = Math.max(width, size);
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
        }
        return width;
    }
}
