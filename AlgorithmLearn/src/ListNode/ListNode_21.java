package ListNode;

public class ListNode_21 {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyNode=new ListNode(-1);
        ListNode cur=dummyNode;
        while (l1!=null&&l2!=null){
            if (l1.val>=l2.val){
                //放l2
                cur.next=l2;
                ListNode next=l2.next;//获取下一个节点
                l2.next=null;//取消当前节点的连接
                l2=next;//指向下一个节点
                cur=cur.next;
            }else{
                cur.next=l1;
                ListNode next=l1.next;//获取下一个节点
                l1.next=null;//取消当前节点的连接
                l1=next;//指向下一个节点
                cur=cur.next;
            }
        }
        while (l1!=null){
            cur.next=l1;
            ListNode next=l1.next;//获取下一个节点
            l1.next=null;//取消当前节点的连接
            l1=next;//指向下一个节点
            cur=cur.next;
        }
        while (l2!=null){
            cur.next=l2;
            ListNode next=l2.next;//获取下一个节点
            l2.next=null;//取消当前节点的连接
            l2=next;//指向下一个节点
            cur=cur.next;
        }
        return dummyNode.next;
    }
}
