package BinaryTree;

public class BinaryTree_572 {
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
     * 572. 另一个树的子树
     * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。
     * s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return (root!=null&&subRoot!=null)&&(recur(root,subRoot)||isSubtree(root.left,subRoot)||isSubtree(root.right,subRoot));
    }

    public boolean recur(TreeNode A, TreeNode B){
        //子树和子结构的区别
        if (A==null&&B==null)
            return true;
        if ((A==null||B==null)||A.val!=B.val)
            return false;
        return recur(A.left,B.left)&&recur(A.right,B.right);
    }
}
