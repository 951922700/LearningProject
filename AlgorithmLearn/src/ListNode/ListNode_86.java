package ListNode;

public class ListNode_86 {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    /**
     * 86. 分隔链表
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     *
     * 你应当 保留 两个分区中每个节点的初始相对位置。
     * 输入：head = [1,4,3,2,5,2], x = 3
     * 输出：[1,2,2,4,3,5]
     *
     * 思路：创建两个链表 一次遍历将所有大于等于x的放进一个链表  其余另一个链表
     */
    public ListNode partition(ListNode head, int x) {
        ListNode small=new ListNode();
        ListNode smallHead=small;
        ListNode large=new ListNode();
        ListNode largeHead=large;
        ListNode cur=head;
        while (cur!=null){
            if (cur.val>=x){
                largeHead.next=cur;
                cur=cur.next;
                largeHead.next.next=null;//取掉添加节点对下一个节点的连接
                largeHead=largeHead.next;
            }else{
                smallHead.next=cur;
                cur=cur.next;
                smallHead.next.next=null;//取掉添加节点对下一个节点的连接
                smallHead=smallHead.next;
            }
        }
        smallHead.next=large.next;
        return small.next;
    }


}
