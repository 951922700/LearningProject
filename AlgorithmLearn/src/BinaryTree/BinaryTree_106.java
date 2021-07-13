package BinaryTree;

import java.util.HashMap;
import java.util.Map;

public class BinaryTree_106 {
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
     * 106. 从中序与后序遍历序列构造二叉树
     * 根据一棵树的中序遍历与后序遍历构造二叉树。
     *
     * 注意:
     * 你可以假设树中没有重复的元素。
     * 中序遍历 inorder = [9,3,15,20,7]  左中右
     * 后序遍历 postorder = [9,15,7,20,3]  左右中
     */
    Map<Integer,Integer> map=new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n=inorder.length;
        for (int i = 0; i <n ; i++) {
            map.put(inorder[i],i);
        }
        return recur(inorder,postorder,0,n-1,0,n-1);
    }

    public TreeNode recur(int[] inorder, int[] postorder,int i_left,int i_right,int p_left,int p_right){
        if (p_left>p_right)
            return null;
        int p_root=p_right;//后序遍历  最右边根节点
        int i_root=map.get(postorder[p_right]);//根在前序的索引

        TreeNode root=new TreeNode(postorder[p_root]);
        int size=i_root-i_left;//左子树节点个数

        root.left=recur(inorder,postorder,i_left,i_root-1,p_left,p_left+size-1);
        root.right=recur(inorder,postorder,i_root+1,i_right,p_left+size,p_root-1);
        return root;
    }
}
