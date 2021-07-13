package BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree_1382 {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    /**
     * 1382. 将二叉搜索树变平衡
     * 给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。
     *
     * 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是 平衡的 。
     *
     * 如果有多种构造方法，请你返回任意一种。
     *
     *
     */
    List<Integer> inorder=new ArrayList<>();
    public TreeNode balanceBST(TreeNode root) {
        getInorder(root);
        return recur(0,inorder.size()-1);
    }

    /**
     * 左中右
     * @param root
     */
    public void getInorder(TreeNode root){
        if (root==null)
            return ;
        getInorder(root.left);
        inorder.add(root.val);
        getInorder(root.right);
    }

    public TreeNode recur(int left,int right){
        if (left>right)
            return null;
        int mid=right+(left-right)/2;
        TreeNode root=new TreeNode(inorder.get(mid));
        root.left=recur(left,mid-1);
        root.right=recur(mid+1,right);
        return root;
    }
}
