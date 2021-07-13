package BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTree_144 {
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
  List<Integer> ans=new ArrayList<>();
    /**
     * 前序遍历
     * 中左右
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        dfs(root);
       return ans;
    }

    /**
     * 递归
     * 中左右
     * @param root
     */
    public void dfs(TreeNode root){
        if (root==null)
            return;
        ans.add(root.val);
        dfs(root.left);
        dfs(root.right);
    }

    /**
     * 显示递归
     * 中左右
     */
    public void dfs_(TreeNode root){
        Deque<TreeNode> stack=new ArrayDeque<>();
        while (root!=null||stack.isEmpty()){
            while (root!=null) {
                ans.add(root.val);
                stack.push(root);
                root = root.left;
            }
        root=stack.pop();
        root=root.right;
        }
    }
}
