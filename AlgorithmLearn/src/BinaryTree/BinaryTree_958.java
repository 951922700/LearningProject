package BinaryTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree_958 {
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
     * 记录序号
     */
    class Tree{
        TreeNode root;
        int sign;

        public Tree(TreeNode root, int sign) {
            this.root = root;
            this.sign = sign;
        }
    }
    /**
     * 958. 二叉树的完全性检验
     * 给定一个二叉树，确定它是否是一个完全二叉树。
     *
     * 百度百科中对完全二叉树的定义如下：
     *
     * 若设二叉树的深度为 h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，
     * 第 h 层所有的结点都连续集中在最左边，这就是完全二叉树。（注：第 h 层可能包含 1~ 2h 个节点。）
     *
     *节点规律 i节点  左节点i*2  右节点i*2+1
     * O(N)
     */
    public boolean isCompleteTree(TreeNode root) {
        List<Tree> list=new LinkedList<>();
        Tree tree=new Tree(root,1);
        Queue<Tree> queue=new LinkedList<>();
        queue.offer(tree);

        while (!queue.isEmpty()){
            Tree t=queue.poll();
            list.add(t);
            if (t.root.left!=null)
                queue.offer(new Tree(t.root.left,2*t.sign));
            if (t.root.right!=null)
                queue.offer(new Tree(t.root.right,2*t.sign+1));
        }

        return list.get(list.size()-1).sign==list.size();
    }
}
