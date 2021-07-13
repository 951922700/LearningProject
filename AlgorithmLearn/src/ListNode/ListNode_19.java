package ListNode;

public class ListNode_19 {
    /**
     * 19. 删除链表的倒数第 N 个结点
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     *
     * 进阶：你能尝试使用一趟扫描实现吗？
     */
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head==null)
            return null;
        ListNode nummyNode=new ListNode(-1);//添加一个虚拟节点   免去了如果删除节点是头结点的判断
        nummyNode.next=head;
        ListNode slow=nummyNode,fast=nummyNode;
        int num=n+1;
        //循环n次的话找到的是倒数第n个节点   但是我们要找的是前面一个循环n+1次
        while (num--!=0){
            fast=fast.next;
        }
        while (fast!=null){
            slow=slow.next;
            fast=fast.next;
        }
        ListNode next=slow.next;
        slow.next=next.next;
        next.next=null;
        return nummyNode.next;
    }
}
