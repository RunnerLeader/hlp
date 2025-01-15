package com.note.graph;

import com.note.graph.grap.Graph;
import com.note.graph.grap.Node;

import java.util.*;

/**
 * 拓扑排序
 */
public class TopSort {

    public static void main(String[] args) {

    }

    //有向图 有入度为0的点 且没有环
    public static List<Node> topSort(Graph graph) {
        //key 某一个Node
        //value  node的入度
        Map<Node, Integer> inMap = new HashMap<>();
        //入度为0的队列
        Queue<Node> zeroQueue = new LinkedList<>();

        for (Node node : graph.nodes.values()) {
            if (node.in == 0) {
                zeroQueue.offer(node);
            }
            inMap.put(node, node.in);
        }

        List<Node> list = new ArrayList<>();
        while (!zeroQueue.isEmpty()) {
            Node poll = zeroQueue.poll();
            list.add(poll);
            for (Node next : poll.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0){
                    zeroQueue.offer(next);
                }
            }
        }
        return list;
    }
}
