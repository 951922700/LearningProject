package QueueAndStack.Stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Stack_dfs_133 {
    /**
     * 133. 克隆图
     * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
     *
     * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
     *
     * class Node {
     *     public int val;
     *     public List<Node> neighbors;
     * }
     */
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    public Node cloneGraph(Node node) {
        if (node==null)
            return node;
        HashMap<Node,Node> visited=new HashMap<>();//key是原来的node  value是克隆的node
        return dfs(node,visited);
    }

    public Node dfs(Node node,HashMap<Node,Node> visited){
        if (node==null)
            return node;
        //如果被访问过就是被克隆过了 直接返回
        if (visited.containsKey(node)){
            return visited.get(node);
        }
        Node cloneNode=new Node(node.val,new ArrayList<>());
        visited.put(node,cloneNode);
        //对每个邻居进行克隆
        for (Node neighbor:node.neighbors) {
            cloneNode.neighbors.add(dfs(neighbor,visited));
        }
        return cloneNode;
    }

    public void bfs(){

    }
}
