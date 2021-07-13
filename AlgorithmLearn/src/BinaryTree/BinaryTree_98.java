package BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class BinaryTree_98 {
    public static class TreeNode {
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
     * 98. 验证二叉搜索树
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     *
     * 假设一个二叉搜索树具有如下特征：
     *
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     */
/*    public boolean isValidBST(TreeNode root) {
        if (root==null)
            return true;
        return checkLess(root.left,root.val)&&checkLarge(root.right,root.val)&&isValidBST(root.left)&&isValidBST(root.right);
    }*/

    /**
     * 脑残暴力
     * @param root
     * @param val
     * @return
     */
    public boolean checkLess(TreeNode root,int val){
        if (root==null)
            return true;
        //当前节点开始的所有节点都小于val
        if (root.val>=val)
            return false;
        //再判断左右子树的情况
        return checkLess(root.left,val)&&checkLess(root.right,val);
    }

    public boolean checkLarge(TreeNode root,int val){
        if (root==null)
            return true;
        //当前节点开始的所有节点都大于val
        if (root.val<=val)
            return false;
        //再判断左右子树的情况
        return checkLarge(root.left,val)&&checkLarge(root.right,val);
    }



    /**
     *一次递归
     * 不断改变左右区间
     * O(N)
     */
    public  boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

    /**
     * 中序遍历
     * 二叉搜索树中序遍历一定是升序的
     * 左中右
     * O(N)
     */
    public  boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack=new LinkedList<>();
        double pre=-Double.MAX_VALUE;
        while (root!=null||!stack.isEmpty()){
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
            root=stack.pop();
            if (root.val<=pre)
                return false;
            pre=root.val;
            root=root.right;
        }
        return true;
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode(5);
        TreeNode root2=new TreeNode(1);
        TreeNode root3=new TreeNode(6);
        root.left=root2;
        root2.right=root3;

    }

}
