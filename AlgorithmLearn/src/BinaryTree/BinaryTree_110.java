package BinaryTree;

public class BinaryTree_110 {
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
     * 110. 平衡二叉树
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     *
     * 本题中，一棵高度平衡二叉树定义为：
     *
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
     *
     * 自顶向下递归
     * 要对每个节点计算两次高度
     * 时间 最坏情况是变成链表
     * 空间O(n)
     */
    public boolean isBalanced(TreeNode root) {
        if (root==null)
            return true;
        return Math.abs(getHeight(root.left)-getHeight(root.right))<=1&&isBalanced(root.left)&&isBalanced(root.right);
        //return height(root)!=-1;
    }

    /**
     * 获取某节点所在子树高度
     * @param root
     * @return
     */
    public int getHeight(TreeNode root){
        if (root==null)
            return 0;
        return Math.max(getHeight(root.left),getHeight(root.right))+1;
    }

    /**
     * 自底向上
     * 理解自底向上：就是不断递归深入  但是不是直接算出某个节点的高度
     * 而是直到叶子节点，然后递归到的每个节点判断完成之后返回-1或者返回过程+1
     * @param root
     * @return
     * 时间O(N)
     */
    public int height(TreeNode root){
        if (root==null)
            return 0;
        int leftHeight=height(root.left);
        int rightHeight=height(root.right);
        if (leftHeight==-1||rightHeight==-1||Math.abs(leftHeight-rightHeight)>1)
            return -1;
        return Math.max(leftHeight,rightHeight)+1;
    }
}
