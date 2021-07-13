package QueueAndStack.Queue;

import java.util.LinkedList;
import java.util.Queue;

public class queue_bfs_111 {
    /**
     * 111. 二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     *
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     *
     * 说明：叶子节点是指没有子节点的节点。
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public int minDepth(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        //如果他左右子树都是null就是叶子节点  第一个满足条件的就是最小深度
        if (root==null) return 0;
        int depth=1;
        queue.offer(root);
        while(!queue.isEmpty()){
            int size=queue.size();
            for (int i=0;i<size;i++){
                TreeNode t=queue.poll();
                if (t.left==null&&t.right==null)
                    return depth;
                if (t.left!=null)
                queue.offer(t.left);
                if (t.right!=null)
                queue.offer(t.right);
            }
            depth++;
        }
        return -1;
    }
}
