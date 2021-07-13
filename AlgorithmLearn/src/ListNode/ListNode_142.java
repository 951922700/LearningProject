package ListNode;

import java.util.HashSet;

public class ListNode_142 {
    class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }
    /**
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null
     */
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> visited=new HashSet<>();
        ListNode pre=head;
        while (pre!=null){
            if (visited.contains(pre)){
                //含有  说明有环
                return pre;
            }
            visited.add(pre);
            pre=pre.next;
        }
        return null;
    }
    /**
     * 快慢指针
     * 用数学推导
     * O(1)空间
     */
    public ListNode detectCycle1(ListNode head) {
        if (head==null||head.next==null)
            return null;
        ListNode slow=head,fast=head;
        while (fast!=null){
            slow=slow.next;
            if (fast.next!=null){
                fast=fast.next.next;
            }else{
                //到结尾了没有环路
                return null;
            }
            if (fast==slow){
                //相遇了
                ListNode pre=head;
                while (pre!=slow){
                    pre=pre.next;
                    slow=slow.next;
                }
                return pre;
            }
        }
        return null;
    }
}
