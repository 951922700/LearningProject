package BinaryTree;

public class BinaryTree_112 {
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
     * 112. 路径总和
     * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，
     * 判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
     *
     * 叶子节点 是指没有子节点的节点。
     */
    boolean isHave=false;
    public boolean hasPathSum(TreeNode root, int targetSum) {
        dfs(root,targetSum,0);
        return isHave;
    }

    public void dfs(TreeNode root,int target,int sum){
        if (isHave)
            return;
        if (root!=null){
            sum+=root.val;
            if (root.left==null&&root.right==null){
                //到了叶子节点  判断相同与否
                isHave=(sum==target);
                return;
            }
            dfs(root.left,target,sum);
            dfs(root.right,target,sum);
        }
    }
}
