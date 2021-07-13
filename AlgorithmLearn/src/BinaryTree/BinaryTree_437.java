package BinaryTree;

public class BinaryTree_437 {
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
     * 437. 路径总和 III
     * 给定一个二叉树，它的每个结点都存放着一个整数值。
     *
     * 找出路径和等于给定数值的路径总数。
     *
     * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     *
     * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
     */
    int num=0;
    public int pathSum(TreeNode root, int targetSum) {
        recur(root,targetSum);
        return num;
    }

    public void recur(TreeNode root, int targetSum){
        if (root==null)//注意这里判空的顺序  如果目标sum为0的话  如果让空的左右子树进去了的话会造成++
            return;
        dfs(root,0,targetSum);
        recur(root.left,targetSum);
        recur(root.right,targetSum);
    }

    public void dfs(TreeNode root,int sum,int target){
        if (root==null)
            return;
        sum+=root.val;
        if (sum==target)//这里的判断也很重要
            num++;
        dfs(root.left,sum,target);
        dfs(root.right,sum,target);
    }
}
