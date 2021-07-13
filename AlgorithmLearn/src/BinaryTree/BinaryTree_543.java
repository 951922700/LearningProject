package BinaryTree;

public class BinaryTree_543 {
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
     * 543. 二叉树的直径
     * 给定一棵二叉树，你需要计算它的直径长度。
     * 一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     *
     * 递归：求左子树和右子树深度
     * 中间判断一下 左子树深度 + 右子树深度 是否大于当前直径
     * 返回的时候选左右最大的一个深度+1
     *
     * 1 2 3   1为根节点  则路径上节点数ans等于左深度+右深度+1
     * 直径为ans-1
     */
    int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        if ((root==null)||(root.left==null&&root.right==null))
            return 0;
        ans=0;
        dfs(root);
        return ans-1;
    }

    public int dfs(TreeNode root){
        if (root==null)
            return 0;
        int lHeight=dfs(root.left);
        int rHeight=dfs(root.right);
        ans=Math.max(ans,lHeight+rHeight+1);
        return Math.max(lHeight,rHeight)+1;
    }
}
