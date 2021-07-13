package BinaryTree;

public class BinaryTree_226 {
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
     * 226. 翻转二叉树
     * 翻转一棵二叉树。
     * O(N)
     */
    public TreeNode invertTree(TreeNode root) {
        if (root==null)
            return null;
        //获取左右子树
        TreeNode tLeft=invertTree(root.left);
        TreeNode tRight=invertTree(root.right);
        root.left=tRight;
        root.right=tLeft;
        return root;
    }
}
