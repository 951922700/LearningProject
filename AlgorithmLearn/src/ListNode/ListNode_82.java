package ListNode;

public class ListNode_82 {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    /**
     * 82. 删除排序链表中的重复元素 II
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
     *
     * 返回同样按升序排列的结果链表。
     *
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyNode=new ListNode(-1);
        int num;
        dummyNode.next=head;
        ListNode cur=dummyNode;
        while (cur.next!=null&&cur.next.next!=null){
            if (cur.next.val==cur.next.next.val){
                num=cur.next.val;
                //删除全部val为num的cur.next 直到不相等或者为空
                while (cur.next!=null&&cur.next.val==num){
                    ListNode next=cur.next;
                    cur.next=next.next;
                    next.next=null;
                }
            }else{
                //没有重复 cur前进
                cur=cur.next;
            }
        }
        return dummyNode.next;
    }
}
