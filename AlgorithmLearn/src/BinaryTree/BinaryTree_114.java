package BinaryTree;

public class BinaryTree_114 {
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
     * 114. 二叉树展开为链表
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     *
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同
     * O(n)不止O（n） 还有找末尾节点的时间
     * O(1)
     */
    /*public void flatten(TreeNode root) {
        root=recur(root);
    }*/

    public TreeNode recur(TreeNode root){
        if (root==null)
            return null;

        TreeNode left=recur(root.left);//获得左节点
        TreeNode right=recur(root.right);//获得有节点
        root.left=null;//设置当前左节点指向null
        if (left==null)
            return root;//1.左边为null 无论右边有没有都不用改动
        //2.left不为null
        root.right=left;//可以想象成将三个节点展开的样子
        if (right==null)//右为空的话 直接返回
            return root;
        //找到最后一个节点
        TreeNode cur=left;
        while (cur.right!=null)
            cur=cur.right;
        cur.right=right;
        return root;
    }

    /**
     * O(n)
     * O(1)
     * 判断当前节点左子树是否为空  为空 不用动
     * 不为空 找到左子树的最深的右子树节点
     * 修改指针
     * @param root
     */
    public void flatten(TreeNode root) {
        TreeNode cur=root;
        while (cur!=null){
            if (cur.left!=null){
                TreeNode left=cur.left;
                TreeNode pre=left





                        ;
                while (pre.right!=null)
                    pre=pre.right;
                pre.right=cur.right;
                cur.left=null;
                cur.right=left;
            }
            cur=cur.right;
        }
    }
}
