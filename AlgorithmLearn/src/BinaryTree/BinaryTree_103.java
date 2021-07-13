package BinaryTree;

import java.util.*;

public class BinaryTree_103 {
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
     * 103. 二叉树的锯齿形层序遍历
     * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     *
     * 另一种解法是不用改变遍历的顺序  也就是节点的遍历一直保持从左到右
     * 然后结果用双端队列存
     * 如果这层是从左到右 用offerLast
     * 如果这层是从右到左 用offerFirst
     *
     * 当前代码是修改了遍历节点的顺序
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list=new ArrayList<>();
        if (root==null)
            return list;
        Queue<TreeNode> queue=new ArrayDeque<>();
        queue.offer(root);
        int dir=0;//控制方向 0右边到左边  1反
        while (!queue.isEmpty()){
            int size=queue.size();
            List<Integer> ans=new ArrayList<>();
            Deque<TreeNode> stack=new ArrayDeque<>();
            for (int i = 0; i <size ; i++) {
                TreeNode node=queue.poll();
                ans.add(node.val);
                //用栈记录从左到右或者右到左添加的节点
                stack.push(node);
            }
            //对每个节点按顺序放左右子树
            while (!stack.isEmpty()){
                TreeNode node=stack.poll();
                //判断左到右放还是···
                if (dir==0){
                    //右边到左边
                    if (node.right!=null)
                        queue.offer(node.right);
                    if (node.left!=null)
                        queue.offer(node.left);
                }else{
                    if (node.left!=null)
                        queue.offer(node.left);
                    if (node.right!=null)
                        queue.offer(node.right);
                }
            }
            dir=dir==0?1:0;//1变0  0变1
            list.add(ans);
        }
        return list;
    }
}
