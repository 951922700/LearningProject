package BinaryTree;

public class BinaryTree_109 {
        public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }
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
     * 109. 有序链表转换二叉搜索树
     * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
     *
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     * 给定的有序链表： [-10, -3, 0, 5, 9],
     *
     * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
     *
     时间复杂度：O(n \log n)O(nlogn)，其中 nn 是链表的长度。

     设长度为 nn 的链表构造二叉搜索树的时间为 T(n)T(n)，递推式为 T(n) = 2 \cdot T(n/2) + O(n)T(n)=2⋅T(n/2)+O(n)，
     根据主定理，T(n) = O(n \log n)T(n)=O(nlogn)。

     空间复杂度：O(\log n)O(logn)，这里只计算除了返回答案之外的空间。
     平衡二叉树的高度为 O(\log n)O(logn)，即为递归过程中栈的最大深度，也就是需要的空间。

     */
  /*  public TreeNode sortedListToBST(ListNode head) {
        return recur(head,null);
    }*/

    /**
     * left  right
     * 一是防止head参数被破坏
     * 二是方便断绝与mid的关系
     * @param left
     * @param right  右边边界 这个右不包含
     * @return
     */
    public TreeNode recur(ListNode left,ListNode right){
        if (left==right)
            return null;

        //找中间节点
        ListNode slow=left,fast=left;
        while (fast.next!=right&&fast.next.next!=right){
            fast=fast.next.next;
            slow=slow.next;
        }
        ListNode mid=slow;
        TreeNode root=new TreeNode(mid.val);//根节点
        root.left=recur(left,mid);
        root.right=recur(mid.next,right);
        return root;
    }

    public TreeNode sortedListToBST(ListNode head) {
        globalNode=head;
        ListNode cur=head;
        int n=0;
        while (cur!=null){
            cur=cur.next;
            n++;
        }
        return dfs(0,n-1);
    }

    /**
     * 直接按照中序遍历的顺序进行构造就好了
     */
    ListNode globalNode;

    /**
     * 左根右
     * @param left
     * @param right
     * @return
     * 时间O(n)
     */
    public TreeNode dfs(int left,int right){
        if (left>right)
            return null;
        TreeNode root=new TreeNode();//提前构造占位置
        int mid=right+(left-right)/2;
        root.left=dfs(left,mid-1);
        root.val=globalNode.val;
        globalNode=globalNode.next;
        root.right=dfs(mid+1,right);
        return root;
    }
}
