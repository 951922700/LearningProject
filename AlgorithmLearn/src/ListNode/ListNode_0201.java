package ListNode;

import java.util.HashSet;

public class ListNode_0201 {
  public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
    /**
     * 面试题 02.01. 移除重复节点  没有排序的
     * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点
     * 1.哈希表 O(N) O(N)
     * 2.双重循环暴力 O(N平方) O(1)
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        HashSet<Integer> visited=new HashSet<>();
        ListNode dummyNode=new ListNode(-1);
        dummyNode.next=head;
        ListNode cur=dummyNode;
        while (cur!=null&&cur.next!=null){
            if (visited.contains(cur.next.val)){
                //已经有了  该删除cur.next结点
                ListNode next=cur.next;
                cur.next=next.next;
                next.next=null;
            }else{
                //没有相同的 可以向前走
                visited.add(cur.next.val);
                cur=cur.next;
            }
        }
        return dummyNode.next;
    }
}
