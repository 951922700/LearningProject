package BinaryTree;

public class BinaryTree_offer33 {
    /**
     * 剑指 Offer 33. 二叉搜索树的后序遍历序列
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，
     * 否则返回 false。假设输入的数组的任意两个数字都互不相同。
     */

    /**
     * 递归
     * 时间O(N平方)
     * O(N)  树退化成链表
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        return check(postorder,0,postorder.length-1);
    }
    public boolean check(int[] postorder,int i,int j){
        //后序遍历 左右根  所以根是最后一个
        if (i>=j) return true;
        int p=i;
        //找到第一个大于根节点的节点 p   左子树(i，p-1)   右子树(p,j-1)
        while (postorder[p]<postorder[j]) p++;
        int m=p;//记录该节点
        //右子树所有节点应该大于根节点
        while (postorder[p]>postorder[j]) p++;
        return p==j&&check(postorder,i,m-1)&&check(postorder,m,j-1);
    }

    /**
     * 单调栈
     * 暂时未做
     */

}
