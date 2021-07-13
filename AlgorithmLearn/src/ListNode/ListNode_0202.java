package ListNode;

/**
 * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
 *
 * 注意：本题相对原题稍作改动
 */
    public class ListNode_0202 {
      public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    public int kthToLast(ListNode head, int k) {
        ListNode fast=head,slow=head;
        while (k--!=0){
            fast=fast.next;
        }
        while (fast!=null){
            fast=fast.next;
            slow=slow.next;
        }
        return slow.val;
    }
}
