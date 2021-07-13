package Test;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class test {
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
    public static void main(String[] args) {
     /*   Integer a=new Integer(128);
        Integer d=new Integer(128);
        Integer b=new Integer(127);
        Integer c=new Integer(1);
        System.out.println(a==d);*/
        HashMap<String, String> hashMap = new HashMap<String, String>() {{
            //put(null, null);
        }};
        System.out.println(hashMap.containsKey(null));
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        dfs(root);
        return ans;
    }
    List<Integer> ans=new LinkedList<>();

    /**
     * 左右中
     * @param root
     */
    public void dfs(TreeNode root){
       Deque<TreeNode> stack=new ArrayDeque<>();
       TreeNode temp=null;
       while (root!=null||!stack.isEmpty()){
           while (root!=null){
               stack.push(root);
               root=root.left;
           }
           root=stack.pop();
           //此时左子树为空了
           if (root.right!=null&&root.right!=temp){
               //右子树不为空 放进去遍历
               stack.push(root);
               root=root.right;
           }else{
               //右子树也为空了 可以输出中节点
               ans.add(root.val);
               temp=root;//记录下这个节点
               root=null;
           }
       }
    }
}