package QueueAndStack.Stack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 */
public class stack05 {

    //递归算法
   /* List<Integer> list=new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        dfs(root);
        return list;
    }

    public void dfs(TreeNode node){
        if (node==null) return;
       dfs(node.left);
       list.add(node.val);
       dfs(node.right);
    }*/

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        Deque<TreeNode> stack=new LinkedList<>();
        while (root!=null||!stack.isEmpty()){
            while (root!=null){
                stack.push(root);
                //前序遍历的时候在这里add，而且上面把右子树压栈
                root=root.left;
            }
            root=stack.pop();
            list.add(root.val);
            root=root.right;
        }
        return list;
    }

    /**前序算法
     *     vector<int> preorderTraversal(TreeNode* root) {
     *         stack<TreeNode*> S;
     *         vector<int> v;
     *         TreeNode* rt = root;
     *         while(rt || S.size()){
     *             while(rt){
     *                 S.push(rt->right);
     *                 v.push_back(rt->val);
     *                 rt=rt->left;
     *             }
     *             rt=S.top();S.pop();
     *         }
     *         return v;
     *     }
     *
     * @param args
     */
    public static void main(String[] args) {
        stack05 stack05=new stack05();
        TreeNode nodeRight=new TreeNode(2);
        nodeRight.left=new TreeNode(3);
        TreeNode root=new TreeNode(1);
        root.left=null;
        root.right=nodeRight;
        List<Integer> list = stack05.inorderTraversal(root);
        for (Integer temp:list) {
            System.out.printf(temp+" ");
        }
    }

}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}