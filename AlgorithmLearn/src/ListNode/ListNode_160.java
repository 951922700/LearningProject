package ListNode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 *
 * 图示两个链表在节点 c1 开始相交：
 */
public class ListNode_160 {

  public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }

    /**
     * 简单hashmap实现
     * @param headA
     * @param headB
     * @return
     * O(m+n)
     * O(m)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set=new HashSet<>();
        ListNode pre=headA;
        while (pre!=null){
            set.add(pre);
            pre=pre.next;
        }
        pre=headB;
        while (pre!=null){
            if (set.contains(pre)){
                return pre;
            }
            pre=pre.next;
        }
        return null;
    }

    /**
     * 实现O(1)空间
     * a+c
     * b+c
     * 去看题解吧
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA==null||headB==null)
            return null;
        ListNode l1=headA;
        ListNode l2=headB;
        while (l1!=l2){
            l1=l1==null?headB:l1.next;
            l2=l2==null?headA:l2.next;
        }
        return l1;//null或者相同节点
    }
}
