package BinaryTree;

public class BinaryTree_offer26 {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    /**
     * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     *
     * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
     *
     * 思路：双重递归 一个递归判断A B是否相同  一个递归不断A左右子树与B比较
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        //因为空树不是任意一个树的子结构
        //还得递归判断B是不是A的左子树或者右子树的子结构
        //加上括号 是为了不用进入后面的递归
        return (A!=null&&B!=null)&&(recur(A,B)||isSubStructure(A.left,B)||isSubStructure(A.right,B));
    }

    public boolean recur(TreeNode A, TreeNode B){
        //B为空说明 前面的遍历都相同
        if (B==null) return true;
        //此时B不为空  A为空的话就不同结构了   或者AB值不同
        if (A==null||A.val!=B.val) return false;
        //继续往下比较左右子树
        return recur(A.left,B.left)&&recur(A.right,B.right);
    }
}
