package com.note.graph.grap;

import java.util.*;

public class GraphGenerator {

    /**
     * 转化图
     *
     * @param matrix  [weight, fromNodeVal, toNodeVal]
     */
    public static Graph createGraph(int[][] matrix){
        Graph graph = new Graph();
        for (int[] nums : matrix){
            int weight = nums[0];
            int fromValue = nums[1];
            int toValue = nums[2];
            if (!graph.nodes.containsKey(fromValue)){
                graph.nodes.put(fromValue, new Node(fromValue));
            }
            if (!graph.nodes.containsKey(toValue)){
                graph.nodes.put(toValue, new Node(toValue));
            }
            Node fromNode = graph.nodes.get(fromValue);
            Node toNode = graph.nodes.get(toValue);
            Edge edge = new Edge(weight, fromNode, toNode);
            fromNode.out++;
            fromNode.nexts.add(toNode);
            fromNode.edges.add(edge);
            toNode.in++;
            graph.edges.add(edge);
        }
        return graph;
    }

    //宽度优先遍历
    public void bfs(Node node){
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        queue.offer(node);
        set.add(node);
        while (!queue.isEmpty()){
            Node curr = queue.poll();
            System.out.println(curr.val);
            for (Node next : curr.nexts) {
                if (!set.contains(next)){
                    queue.offer(next);
                    set.add(next);
                }
            }
        }
    }

    //深度优先遍历
    public void dfs(Node node){
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.val);
        while (!stack.empty()){
            Node pop = stack.pop();
            for (Node next : pop.nexts) {
                if (!set.contains(next)){
                    stack.push(pop);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.val);
                    break;
                }
            }
        }
    }

}
