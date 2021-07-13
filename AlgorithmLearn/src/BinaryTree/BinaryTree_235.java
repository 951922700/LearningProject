package BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree_235 {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    /**
     * 235. 二叉搜索树的最近公共祖先
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
     * 最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     */

    /**
     * 方法1  分两次遍历
     * 首先找到两个节点  找节点的同时记录路径
     * 然后找到两个路径的分叉点
     *
     * O(N)
     * 空间O(H)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pPath=getPath(root,p);
        List<TreeNode> qPath=getPath(root,q);
        TreeNode ans=null;
        for (int i = 0; i <pPath.size()&&i<qPath.size() ; i++) {
            //一直相同一直替换  直到分叉点
            if (pPath.get(i)==qPath.get(i))
                ans=pPath.get(i);
            else
                break;
        }
        return ans;
    }

    public List<TreeNode> getPath(TreeNode root,TreeNode target){
        List<TreeNode> path=new ArrayList<>();
        TreeNode node=root;
        while (node!=target){
            path.add(node);
            if (target.val<node.val)
                node=node.left;
            else
                node=node.right;
        }
        path.add(node);//加上当前节点
        return path;
    }

    /**
     * 一次遍历
     * 要么同时小于
     * 要么同时大于
     * 如果不满足则为分叉点
     * 出现等于的话 就是两个节点其中一个为公共祖先
     * O(N)
     * O(1)
     */
    public TreeNode find(TreeNode root,TreeNode p, TreeNode q){
        TreeNode node=root;
        while (true){
            if (p.val<node.val&&q.val<node.val)
                node=node.left;
            else if (p.val>node.val&&q.val>node.val)
                node=node.right;
            else
                break;
        }
        return node;
    }
}
