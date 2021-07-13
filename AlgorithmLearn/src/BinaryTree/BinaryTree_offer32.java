package BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BinaryTree_offer32 {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    /**
     * 剑指 Offer 32 - I. 从上到下打印二叉树
     * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
     */
    public int[] levelOrder(TreeNode root) {
        if (root==null)
            return new int[0];
        List<Integer> ans=new ArrayList<>();
        Queue<TreeNode> queue=new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            for (int i=0;i<size;i++){
                TreeNode temp=queue.poll();
                ans.add(temp.val);
                if (temp.left!=null)
                    queue.offer(temp.left);
                if (temp.right!=null)
                    queue.offer(temp.right);
            }
        }
        int[] res=new int[ans.size()];
        for (int i = 0; i <res.length ; i++) {
            res[i]=ans.get(i);
        }
        return res;
    }
}
