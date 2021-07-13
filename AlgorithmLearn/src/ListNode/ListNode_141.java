package ListNode;

import java.util.HashSet;

public class ListNode_141 {
    /**
     * 环形链表
     * 给定一个链表，判断链表中是否有环。
     */
    static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
   }

    /**
     * time O(N)
     * memory O(N)
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        HashSet<ListNode> visited=new HashSet<>();
        ListNode pre=head;
        while (pre!=null){
            if (visited.contains(pre)){
                //含有  说明有环
                return true;
            }
            visited.add(pre);
            pre=pre.next;
        }
        return false;
    }

    /**
     * 快慢指针
     * 龟兔赛跑
     * O(N)
     * O(1)
     */
    public static boolean hasCycle1(ListNode head) {
        if (head==null||head.next==null)
            return false;
        ListNode slow=head,fast=head.next;
        while (slow!=fast){
            if (fast==null||fast.next==null)
                return false;
            slow=slow.next;
            fast=fast.next.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        System.out.println(hasCycle(head));
    }
}
