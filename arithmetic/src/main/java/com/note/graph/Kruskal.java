package com.note.graph;

import com.note.graph.grap.Edge;
import com.note.graph.grap.Graph;
import com.note.graph.grap.Node;

import java.util.*;

public class Kruskal {
    //无向图

    static class MySet{
        Map<Node, Set<Node>> map = new HashMap<>();

        public MySet(List<Node> nodes) {
            for (Node node : nodes) {
                Set<Node> set = new HashSet<>();
                set.add(node);
                map.put(node, set);
            }
        }

        public boolean isSameSet(Node fromNode, Node toNode){
            Set<Node> fromSet = map.get(fromNode);
            Set<Node> toSet = map.get(toNode);
            return fromSet == toSet;
        }

        public void union(Node fromNode, Node toNode){
            Set<Node> fromSet = map.get(fromNode);
            fromSet.addAll(map.get(toNode));
            map.put(toNode, fromSet);
        }
    }

    public static Set<Edge> kMST(Graph graph){
        MySet set = new MySet(new ArrayList<>(graph.nodes.values()));
        PriorityQueue<Edge> edgeQueue = new PriorityQueue<>(new Cpx());
        for (Edge edge : graph.edges) {
            edgeQueue.add(edge);
        }

        Set<Edge> result = new HashSet<>();

        for (Edge edge : edgeQueue) {
            if (!set.isSameSet(edge.from, edge.to)){
                result.add(edge);
                set.union(edge.from, edge.to);
            }
        }
        return result;
    }

    static class Cpx implements Comparator<Edge>{
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }


}
