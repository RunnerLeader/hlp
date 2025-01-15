package com.note.graph.grap;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int val;
    public int in;
    public int out;
    public List<Node> nexts; //从自己出发直接相邻的点
    public List<Edge> edges;//自己拥有的边

    public Node(int val) {
        this.val = val;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}