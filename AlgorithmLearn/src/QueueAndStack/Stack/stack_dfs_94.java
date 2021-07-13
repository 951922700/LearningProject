package QueueAndStack.Stack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class stack_dfs_94 {
    /**
     * 94. 二叉树的中序遍历
     * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
     *
     * 前序 中左右
     * 中序 左中右
     * 后序 左右中
     */
    public class TreeNode {
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

    List<Integer> list=new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        dfsMiddle(root);
        return list;
    }
    /**
     * 中序  左中右
     * 递归
     */
    public void dfsMiddle(TreeNode root){
        if (root==null)
            return;
        dfsMiddle(root.left);
        list.add(root.val);
        dfsMiddle(root.right);
    }

    /**
     * 显示利用栈进行中序遍历
     * 左中右
     * 1.不断把左子节点压入栈中，退出循环  此时root节点指向null
     * 2.循环退出后栈弹出并加入到list中
     * 3.然后root指向right
     * 4.栈没空或者root不为空就继续123步骤循环
     * @param root
     */
    public void dfsMiddles(TreeNode root){
        Deque<TreeNode> stack=new LinkedList<>();
        while (root!=null||!stack.isEmpty()){
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
            //此时root指向null
            root=stack.pop();
            list.add(root.val);
            root=root.right;
        }
    }

    /**
     * 前序递归  中左右
     */
    public void dfsFront(TreeNode root){
        if (root==null)
            return;
        list.add(root.val);
        dfsFront(root.left);
        dfsFront(root.right);
    }

    /**
     * 前序 显示递归 中左右
     * 1.访问该节点 加入list中 以及栈中 然后遍历他的左子树
     * 2.当他的左子树遍历完（此时中的点已经访问完） 指向右节点
     * 3.进入12循环 直到root为空以及栈为空
     */
    public void dfsFronts(TreeNode root){
        Deque<TreeNode> stack=new LinkedList<>();
        while (root!=null||!stack.isEmpty()){
            while (root!=null){
                list.add(root.val);
                stack.push(root);
                root=root.left;
            }
            //此时root指向null
            root=stack.pop();
            root=root.right;
        }
    }

    /**
     * 后序 左右中
     * 最难
     */
    public void dfsAfters(TreeNode root){
        Deque<TreeNode> stack=new LinkedList<>();
        TreeNode pre=null;
        while (root!=null||!stack.isEmpty()){
            //1.不断遍历左子树
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
            //2.此时root为空  出栈看右子树情况
            root=stack.pop();
            if (root.right!=null&&root.right!=pre){
                //3.如果右子树不为空(且右子树不是刚访问过的) 将当前节点再次入栈 防止回不来
                stack.push(root);
                root=root.right;
            }else{
                //4.当前节点右子树为空，且左子树已经遍历完了  可以加入list中
                list.add(root.val);
                //5.这里如果不将当前这个节点标记的话 那下一次弹出来的节点的右节点又是非空的又会执行3操作  所以需要一个标记
                pre=root;
                //6.这里如果不把root置null的话又会遍历一次左树
                root=null;
            }
        }
    }
}
