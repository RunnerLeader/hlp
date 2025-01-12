package com.note.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 完全二叉树
 * 1 任一节点有右无左 就不是
 * 2 在满足1情况下 当遇到左右节点不双全的节点 那么接下来遇到的节点都必须是叶子节点  否则不是
 *
 * 定义
 * 任何一个节点都是双全 或 将要双全
 * 左右数深度差 < 2
 */
public class CBTree {

    public boolean isCBT(TreeNode root){
        if (root == null){
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        boolean flag = false;
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if ((flag && (node.left != null || node.right != null)) // 不双全节点之后  居然又发现节点不是叶节点
                    || (node.left == null && node.right != null)){//当前节点无左有右
                return false;
            }
            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
            if (node.left == null || node.right == null){
                flag =true;
            }
        }
        return true;
    }


}
