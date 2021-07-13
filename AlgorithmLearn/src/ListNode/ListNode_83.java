package ListNode;

import java.util.HashSet;

public class ListNode_83 {
    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    /**
     * 83. 删除排序链表中的重复元素
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
     *
     * 返回同样按升序排列的结果链表。
     */
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode cur=head;
        while (cur!=null){
            if (cur.next!=null&&cur.val==cur.next.val){
                ListNode next=cur.next;
                cur.next=next.next;
                next.next=null;
                //cur不移动
            }else{
                //不相等cur迁移
                cur=cur.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        ListNode head2=new ListNode(1);
        ListNode head3=new ListNode(1);
        head.next=head2;
        head2.next=head3;
        head=deleteDuplicates(head);
       // while (head)
    }
}
