package BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTree_145 {
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
     * 145. 二叉树的后序遍历
     * 左右中
     */
    List<Integer> ans=new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        dfs_(root);
        return ans;
    }

    public void dfs(TreeNode root){
        if (root==null)
            return;
        dfs(root.left);
        dfs(root.right);
        ans.add(root.val);
    }

    /**
     * 左右中
     * @param root
     */
    public void dfs_(TreeNode root){
        Deque<TreeNode> stack=new ArrayDeque<>();
        TreeNode pre=null;
        while (root!=null||!stack.isEmpty()){
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
            root=stack.pop();
            if (root.right!=null&&pre!=root.right){
                stack.push(root);
                root=root.right;
            }else{
                //没有右节点且左节点遍历完
                ans.add(root.val);
                pre=root;
                root=null;
            }
        }
    }
}
