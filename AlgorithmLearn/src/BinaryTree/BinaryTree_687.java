package BinaryTree;

public class BinaryTree_687 {
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
     * 687. 最长同值路径
     * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
     *
     * 注意：两个节点之间的路径长度由它们之间的边数表示。
     */
    int sum=0;
 /*   public int longestUnivaluePath(TreeNode root) {
        if ((root==null)||(root.left==null&&root.right==null))
            return 0;
        dfs(root);
        return sum-1;
    }*/

    /**
     * 双重递归 不大行  可以和求直径一样一次遍历 自底向上
     * @param root
     */
    public void dfs(TreeNode root){
        //让每个节点做根节点
        if (root==null)
            return;
        recur(root,root.val);
        dfs(root.left);
        dfs(root.right);
    }

    public int recur(TreeNode root,int val){
        if (root==null)
            return 0;
        //不同值卡住
        if (root.val!=val)
            return 0;
        //值相同 往下
        int lHeight=recur(root.left,val);
        int rHeight=recur(root.right,val);
        sum=Math.max(sum,lHeight+rHeight+1);//看看当前值谁大
        return Math.max(lHeight,rHeight)+1;
    }

    public int longestUnivaluePath(TreeNode root) {
        if ((root==null)||(root.left==null&&root.right==null))
            return 0;
        getHeight(root);
        return sum-1;
    }

    /**
     * 控制回头时返回的长度
     * @param root
     * @return
     */
    public int getHeight(TreeNode root){
        if (root==null)
            return 0;
        //求左右节点高度  但不一定用
        int lHeight=getHeight(root.left);
        int rHeight=getHeight(root.right);
        //自底向上
        int realLeft=0,realRight=0;
        //如果底部节点和左右子树相同的时候 才能加上上面求出来的左右节点  不一样的话就是0
        if (root.left!=null&&root.val==root.left.val)
            realLeft=lHeight+1;
        if (root.right!=null&&root.val==root.right.val)
            realLeft=rHeight+1;
        //在这里试图判断以root为根节点  相同路径的长度情况
        sum=Math.max(sum,realLeft+realRight+1);
        //返回左右节点最大的一个 这里不用再+1了  上面已经加了
        return Math.max(realLeft,realRight);
    }
}
