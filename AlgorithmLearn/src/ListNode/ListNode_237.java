package ListNode;

public class ListNode_237 {
    /**
     * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点
     */
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    /**
     * 修改成后一个节点的值，把后一个节点删除
     * @param node
     */
    public void deleteNode(ListNode node) {
        ListNode next=node.next;
        node.val=next.val;
        node.next=next.next;
        next.next=null;
    }
}
