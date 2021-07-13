package BinaryTree;

import java.util.HashMap;
import java.util.Map;

public class BinaryTree_105 {
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
     * 105. 从前序与中序遍历序列构造二叉树
     * 根据一棵树的前序遍历与中序遍历构造二叉树。
     *
     * 注意:
     * 你可以假设树中没有重复的元素。
     *
     * 例如，给出
     *
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     */
    private Map<Integer,Integer> map= new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n=inorder.length;
        //以值为key  索引为value  使得查找效率O(1)
        for (int i = 0; i <n ; i++) {
            map.put(inorder[i],i);
        }
        return recur(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
    }

    public TreeNode recur(int[] preorder, int[] inorder,int p_left,int p_right,int i_left,int i_right ){
        if (p_left>p_right)
            return null;
        int p_root=p_left;//前序遍历第一个节点为根节点  索引
        int i_root=map.get(preorder[p_root]);//根据值 定位在中序遍历中根节点的索引

        TreeNode root=new TreeNode(preorder[p_root]);//创建根节点
        int size=i_root-i_left;//根节点到左边界的节点数  不包括根

        root.left=recur(preorder,inorder,p_left+1,p_left+size,i_left,i_root-1);
        root.right=recur(preorder,inorder,p_left+size+1,p_right,i_root+1,i_right);
        return root;
    }
}
