package ListNode;


public class ListNode_206 {
    /**
     * 206. 反转链表
     * 反转一个单链表。
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     */
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
  //迭代
   /* public ListNode reverseList(ListNode head) {
        ListNode ans=null;
        while (head!=null){
            ListNode pre=new ListNode(head.val);
            pre.next=ans;
            ans=pre;
            head=head.next;
        }
        return ans;
    }*/

    //递归
    public ListNode reverseList(ListNode head) {
        if (head==null||head.next==null)
            return head;
        //实际这一步是不断向链表深处走 最后返回的是最后一个节点  然后在返回最后一个节点的同时对链表方向修改
        ListNode p=reverseList(head.next);
        head.next.next=head;
        head.next=null;
        return p;
    }
}
