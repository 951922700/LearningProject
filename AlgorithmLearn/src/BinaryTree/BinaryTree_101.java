package BinaryTree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree_101 {
    public static class TreeNode {
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
     * 101. 对称二叉树
     * 给定一个二叉树，检查它是否是镜像对称的。
     */

    public boolean isSymmetric(TreeNode root) {
        if (root==null)
            return true;
        //return check(root.left,root.right);
        return check2(root.left,root.right);
    }

    /**
     * 递归
     * 左子树的左和右子树的右判断是否相同
     * 右子树的左和左子树的右判断是否相同
     * @param left
     * @param right
     * @return
     */
    public boolean check(TreeNode left,TreeNode right){
        if (left==null&&right==null)
            return true;
        else if(left==null||right==null)
            return false;
        else
            return left.val==right.val&&check(left.left,right.right)&&check(left.right,right.left);
    }

    /**
     * 迭代
     * 类似广搜
     * 血的教训  ArrayDeque 的add方法不能添加null  LinkedList才可以
     */
    public static boolean check2(TreeNode p1,TreeNode p2){
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(p1);
        queue.offer(p2);
        while (!queue.isEmpty()){
            TreeNode temp1 = queue.poll();
            TreeNode temp2 = queue.poll();
            if (temp1==null&& temp2==null)
                continue;
            else if(temp1==null||temp2==null||(temp1.val!=temp2.val))
                return false;
            else{
                queue.offer(temp1.left);
                queue.offer(temp2.right);

                queue.offer(temp1.right);
                queue.offer(temp2.left);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        TreeNode root2=new TreeNode(2);
        TreeNode root3=new TreeNode(2);
        TreeNode root4=new TreeNode(3);
        TreeNode root5=new TreeNode(4);
        TreeNode root6=new TreeNode(4);
        TreeNode root7=new TreeNode(3);
        root.left=root2;
        root.right=root3;
        root2.left=root4;
        root2.right=root5;
        root3.left=root6;
        root3.right=root7;
        check2(root.left,root.right);
    }
}
