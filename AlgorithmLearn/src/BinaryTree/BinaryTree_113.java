package BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTree_113 {
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
     * 113. 路径总和 II
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，
     * 找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     *
     * 叶子节点 是指没有子节点的节点。
     */
    List<List<Integer>> ans=new ArrayList<>();
    List<Integer> list=new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root,0,targetSum);
        return ans;
    }

    public void dfs(TreeNode root,int sum,int target){
        if (root!=null){
            sum+=root.val;
            list.add(root.val);
            if (root.left==null&&root.right==null){
                //叶子节点
                if (sum==target)
                    ans.add(new ArrayList<>(list));
            }
            dfs(root.left,sum,target);
            dfs(root.right,sum,target);
            //回溯
            list.remove(list.size()-1);
        }
    }
}
