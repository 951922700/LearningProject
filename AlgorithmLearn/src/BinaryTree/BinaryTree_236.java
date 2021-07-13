package BinaryTree;

public class BinaryTree_236 {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    /**
     * 二叉树  最近公共祖先
     *
     * 用后序遍历理解  递归
     * 当遍历到对应节点则返回
     *
     * 主要思路是自底向上的返回结果的控制
     * 如果左右节点都不为空向上返回当前节点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null||root==p||root==q)
            return root;
        TreeNode left=lowestCommonAncestor(root.left,p,q);
        TreeNode right=lowestCommonAncestor(root.right,p,q);
        if (left==null&&right==null)
            return null;
        if (left==null)
            return right;
        if (right==null)
            return left;
        return root;
    }
}
