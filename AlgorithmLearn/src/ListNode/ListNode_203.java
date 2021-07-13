package ListNode;

public class ListNode_203 {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    /**
     * 203. 移除链表元素
     * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
     *
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyNode=new ListNode(-1);
        dummyNode.next=head;
        ListNode cur=dummyNode;
        while (cur!=null&&cur.next!=null){
            if (cur.next.val==val){
                ListNode next=cur.next;
                cur.next=next.next;
                next.next=null;
            }else{
                //不相等的时候才前移动
                cur=cur.next;
            }
        }
        return dummyNode.next;
    }
}
