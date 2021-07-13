package ListNode;

public class ListNode_offer18 {
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    /**
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     *
     * 返回删除后的链表的头节点。
     */
    public ListNode deleteNode(ListNode head, int val) {
        //ListNode nummyNode=new ListNode()
        ListNode cur=head;
        //判断是否为头结点
        if (cur.val==val){
            return cur.next;
        }
        while (cur!=null&&cur.next!=null){
            ListNode next=cur.next;
            if (next.val==val){
                //该节点需要被删除
                cur.next=next.next;
                next.next=null;
                break;
            }
            cur=cur.next;
        }
        return head;
    }
}
