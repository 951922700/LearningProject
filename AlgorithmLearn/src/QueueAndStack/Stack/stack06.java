package QueueAndStack.Stack;

import sun.misc.Queue;

import java.util.*;

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
public class stack06 {
    HashMap<Node,Node> visited=new HashMap<>();
    //深度优先搜索
/*    public Node cloneGraph(Node node) {
        //为空直接返回
        if (node==null)
            return node;

        //若已经访问过了，返回对应值
        if (visited.containsKey(node))
            return visited.get(node);

        Node cloneNode=new Node(node.val,new ArrayList<>());
        visited.put(node,cloneNode);
        for (Node neighbor:node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        return cloneNode;
    }*/

    //广度优先搜索

    public Node cloneGraph(Node node) {
        if (node==null)
            return node;
        Deque<Node> queue=new LinkedList();
        queue.offer(node);
        //将第一个节点克隆并加入visited
        visited.put(node,new Node(node.val,new ArrayList<>()));
        while (!queue.isEmpty()){
            Node n=queue.poll();
            for (Node neighbor:n.neighbors) {
                if (!visited.containsKey(neighbor)){
                    //如果节点没被克隆，那么进行克隆并且加入visited queue
                    visited.put(neighbor,new Node(neighbor.val,new ArrayList<>()));
                    queue.add(neighbor);
                }
                //把克隆的邻居点放进当前的点
                visited.get(n).neighbors.add(visited.get(neighbor));
            }
        }
        return visited.get(node);
    }
}
