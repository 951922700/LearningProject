package BinaryTree;

public class BinaryTree_root {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    /**
     * 889. 根据前序和后序遍历构造二叉树
     * 返回与给定的前序和后序遍历匹配的任何二叉树。
     *
     *  pre 和 post 遍历中的值是不同的正整数。
     *  pre =  [1,2,4,5,3,6,7],
     *  post = [4,5,2,6,7,3,1]
     */
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        int n=pre.length;
        return recur(pre,post,0,n-1,0,n-1);
    }

    public TreeNode recur(int[] pre, int[] post,int pre_left,int pre_right,int post_left,int post_right ){
        if (pre_left>pre_right)
            return null;
        TreeNode root=new TreeNode(pre[pre_left]);
        //最后一个节点
        if (pre_left==pre_right)
            return root;
        int pre_l_l=pre_left+1;//前序左子树左边界
        int post_l_r=0;//后序遍历 左子树 右边界
        //找到这个边界在post的位置
        for (int i = post_left; i <=post_right ; i++) {
            if (pre[pre_l_l]==post[i]){
                post_l_r=i;
                break;
            }
        }
        int left_num=post_l_r-post_left+1;//左子树数量
        int pre_l_r=pre_l_l+left_num-1;//前序左子树右边界
        root.left=recur(pre,post,pre_l_l,pre_l_r,post_left,post_l_r);
        root.right=recur(pre,post,pre_l_r+1,pre_right,post_l_r+1,post_right-1);
        return root;
    }
}
