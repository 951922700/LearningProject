package BinaryTree;

import java.util.*;

public class BinaryTree_107 {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    /**
     * 107. 二叉树的层序遍历 II
     * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     * 1.一个思路是最后加一个reverse
     * 2.一直在头部添加
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list=new LinkedList<>();//减少头部插入节点消耗
        if (root==null)
            return list;
        Queue<TreeNode> queue=new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            List<Integer> ans=new ArrayList<>();
            for (int i = 0; i <size ; i++) {
                TreeNode node=queue.poll();
                ans.add(node.val);
                if (node.left!=null){
                    queue.offer(node.left);
                }
                if (node.right!=null){
                    queue.offer(node.right);
                }
            }
            list.add(0,ans);
        }
        //Collections.reverse(list);
        return list;
    }
}
