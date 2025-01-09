package com.note.tree;

import java.util.LinkedList;
import java.util.Queue;

public class SerialNode {

    public Queue<String> serialNode(TreeNode root) {
        Queue<String> queue = new LinkedList<>();
        preSerial(root, queue);
        return queue;
    }

    //先序序列化
    public void preSerial(TreeNode node, Queue<String> queue) {
        if (node == null) {
            queue.offer(null);
        } else {
            queue.offer(node.val + "");
            preSerial(node.left, queue);
            preSerial(node.right, queue);
        }
    }

    //先序反序列化
    public TreeNode buildNodeByPreQueue(Queue<String> queue) {
        String value = queue.poll();
        if (value == null) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(value));
        node.left = buildNodeByPreQueue(queue);
        node.right = buildNodeByPreQueue(queue);
        return node;
    }

    //层序序列化
    public Queue<String> curSerial(TreeNode root) {
        Queue<String> serialQueue = new LinkedList<>();
        if (root == null) {
            serialQueue.offer(null);
            return serialQueue;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                serialQueue.offer(node.val + "");
                if (node.left != null) {
                    serialQueue.offer("" + node.left.val);
                    queue.offer(node.left);
                } else {
                    serialQueue.offer(null);
                }
                if (node.right != null) {
                    serialQueue.offer("" + node.right.val);
                    queue.offer(node.right);
                } else {
                    serialQueue.offer(null);
                }
            }
        }
        return serialQueue;
    }

    public TreeNode buildNodeByCurQueue(Queue<String> serialQueue) {
        String rootVal = serialQueue.poll();
        TreeNode root = genrateTreeNode(rootVal);
        Queue<TreeNode> queue = new LinkedList<>();
        if (rootVal != null){
            queue.offer(root);
        }
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            node.left = genrateTreeNode(serialQueue.poll());
            node.right = genrateTreeNode(serialQueue.poll());
            if (node.left!=null){
                queue.offer(node.left);
            }
            if (node.right!=null){
                queue.offer(node.right);
            }
        }
        return root;
    }

    public TreeNode genrateTreeNode(String val){
        if (val == null){
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(val));
        return node;
    }





}