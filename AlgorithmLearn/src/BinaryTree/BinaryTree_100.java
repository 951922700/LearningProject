package BinaryTree;

public class BinaryTree_100 {
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
     * 100. 相同的树
     * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
     *
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     */
    /**
     * 深度优先搜索
     * 时间复杂度：O(min(m,n))O(min(m,n))，
     * 其中 mm 和 nn 分别是两个二叉树的节点数。对两个二叉树同时进行深度优先搜索，
     * 只有当两个二叉树中的对应节点都不为空时才会访问到该节点，因此被访问到的节点数不会超过较小的二叉树的节点数。
     *
     * 空间复杂度：O(min(m,n))O(min(m,n))，
     * 其中 mm 和 nn 分别是两个二叉树的节点数。空间复杂度取决于递归调用的层数，
     * 递归调用的层数不会超过较小的二叉树的最大高度，最坏情况下，二叉树的高度等于节点数
     *
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null&&q==null){
            return true;
        }else if (p==null||q==null){
            return false;
        }else if (p.val!=q.val){
            return false;
        }else{
            return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
        }
    }
    /**
     * 广搜也可以
     *
     * 在弹出来的时候判断值是否相同  然后判断结构也就是左右子树的情况
     */
}
