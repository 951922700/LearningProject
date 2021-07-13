package BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BinaryTree_102 {
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
     * 102. 二叉树的层序遍历
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）
     * 时间 O(n)
     * 空间 O(n)
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list=new ArrayList<>();
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
            list.add(ans);
        }
        return list;
    }
}
