package BinaryTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public  class BinaryTree_993 {
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
     * 993. 二叉树的堂兄弟节点
     * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
     *
     * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
     *
     * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
     *
     * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
     */
    TreeNode xF,yF;
    int xDepth=0,yDepth=0;
    int x,y;
    boolean xFinished,yFinished;
    public boolean isCousins(TreeNode root, int x, int y) {
        this.x=x;
        this.y=y;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        int depth=0;
        judge(root,null,depth);
        while (!queue.isEmpty()){
            int size=queue.size();
            for (int i = 0; i <size ; i++) {
                TreeNode node=queue.poll();
                if (node.left!=null){
                    queue.offer(node.left);
                    judge(node.left,node,depth+1);
                }
                if (node.right!=null){
                    queue.offer(node.right);
                    judge(node.right,node,depth+1);
                }
                if (xFinished&&yFinished)
                    break;
            }
            depth++;
            if (xFinished&&yFinished)
                break;
        }
        //深度一样  父节点不一样才返回true
        return xDepth==yDepth&&xF!=yF;
    }

    //判断节点 是否为x或y
    public void judge(TreeNode root,TreeNode parent,int depth){
        if (root.val==x){
            //为x节点，记录深度和父节点
            xF=parent;
            xDepth=depth;
            xFinished=true;
        }else if (root.val==y){
            yF=parent;
            yDepth=depth;
            yFinished=true;
        }
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        TreeNode root2=new TreeNode(2);
        TreeNode root3=new TreeNode(3);
        TreeNode root4=new TreeNode(4);
        root.left=root2;
        root.right=root3;
        root2.left=root4;
        BinaryTree_993 binaryTree_993=new BinaryTree_993();
        binaryTree_993.isCousins(root,4,3);
    }
}
