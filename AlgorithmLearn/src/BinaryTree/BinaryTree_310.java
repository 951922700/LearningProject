package BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class BinaryTree_310 {
    /**
     *310. 最小高度树
     * 树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
     *
     * 给你一棵包含 n 个节点的树，标记为 0 到 n - 1 。给定数字 n 和一个有 n - 1 条无向边的 edges 列表（每一个边都是一对标签），其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。
     *
     * 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。
     *
     * 请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。
     *
     * 树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
     */


    /**
     *思路：从度为1的节点  也就是叶子节点开始遍历
     *      不断向内bfs
     *      最后一轮的扩散就是最小高读数的根节点
     *      每次扩散的都必须是度为1的节点
     * @param n  节点数
     * @param edges  n行2列
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans=new ArrayList<>();
        if (n==1){
            ans.add(0);
            return ans;
        }
        //保存每个节点的相邻节点
        List<List<Integer>> neighbours=new ArrayList<>();
        int[] degree=new int[n];
        for (int i=0;i<n;i++){
            neighbours.add(new ArrayList<>());
        }
        //统计每个节点的度以及相邻节点  一行两列
        for (int[] edge:edges) {
            degree[edge[0]]++;  //索引 0  1  两个节点相互连接
            degree[edge[1]]++;
            neighbours.get(edge[0]).add(edge[1]);
            neighbours.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> queue=new ArrayDeque<>();
        for (int i = 0; i <n ; i++) {
            if (degree[i]==1){
                queue.add(i);
            }
        }

        while (!queue.isEmpty()){
            int size=queue.size();
            ans=new ArrayList<>();;//保存每一轮扩散的节点   最后一轮为答案
            for (int i = 0; i <size ; i++) {
                int cur=queue.poll();
                ans.add(cur);
                //获取该节点相邻节点
                List<Integer> temp=neighbours.get(cur);
                for (int j = 0; j <temp.size() ; j++) {
                    degree[temp.get(j)]--;//因为当前节点去掉了  所以邻居节点应该度-1
                    if (degree[temp.get(j)]==1)
                        queue.add(temp.get(j));
                }
            }
        }
        return ans;
    }

}
