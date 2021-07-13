package BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTree_94 {
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
     * 中序遍历
     * 左中右
     */
    List<Integer> ans=new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        dfs_(root);
        return ans;
    }

    public void dfs(TreeNode root){
        if (root==null)
            return;
        dfs(root.left);
        ans.add(root.val);
        dfs(root.right);
    }

    public void dfs_(TreeNode root){
        Deque<TreeNode> stack=new ArrayDeque<>();
        while (root!=null||!stack.isEmpty()){
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
            root=stack.pop();
            ans.add(root.val);
            root=root.right;
        }
    }
}
