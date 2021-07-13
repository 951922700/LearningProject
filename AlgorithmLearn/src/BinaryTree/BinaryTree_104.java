package BinaryTree;

public class BinaryTree_104 {
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
     * 104. 二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     *
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     * O(n)
     * 空间O(height)
     */
    int depth=0;
    public int maxDepth(TreeNode root) {
        dfs(root,0);
        return this.depth;
    }

    public void dfs(TreeNode root,int depth){
        if (root==null){
            this.depth=Math.max(this.depth,depth);
            return;
        }
        dfs(root.left,depth+1);
        dfs(root.right,depth+1);
    }
}
