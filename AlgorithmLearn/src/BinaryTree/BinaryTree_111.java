package BinaryTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree_111 {
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
     * 111. 二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     *
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     *
     * 说明：叶子节点是指没有子节点的节点。
     */
    /**
     * 广搜
     * 第一个左右子节点都没有的点就是最小深度
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        if (root==null)
            return 0;
        queue.offer(root);
        int depth=1;
        while (!queue.isEmpty()){
            int size=queue.size();
            for (int i = 0; i <size ; i++) {
                TreeNode cur=queue.poll();
                if (cur.left==null&&cur.right==null)
                    return depth;
                if (cur.left!=null)
                    queue.offer(cur.left);
                if (cur.right!=null)
                    queue.offer(cur.right);
            }
            depth++;
        }
        return 0;
    }
}
