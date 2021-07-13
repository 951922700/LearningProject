package BinaryTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BinaryTree_257 {
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
     *257. 二叉树的所有路径
     * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     */
    List<String> ans=new LinkedList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        dfs(root,"");
        return ans;
    }

    public void dfs(TreeNode root,String path){
      /**
      不能等到空了才判断而是应该判断左子树和右子树都为空才可以
      if (root==null){
            //记录路径
            ans.add(sb.toString());
            return;
        }
        **/
      if (root!=null){
          StringBuilder sb=new StringBuilder(path);
          sb.append(root.val);
          if (root.left==null&&root.right==null){
              ans.add(sb.toString());
              return;
          }
          sb.append("->");
          dfs(root.left,sb.toString());
          dfs(root.right,sb.toString());
      }

    }
}
