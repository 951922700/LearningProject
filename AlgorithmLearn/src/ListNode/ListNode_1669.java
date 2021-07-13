package ListNode;

public class ListNode_1669 {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    /**
     * 1669. 合并两个链表
     * 给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。
     *
     * 请你将 list1 中第 a 个节点到第 b 个节点删除，并将list2 接在被删除节点的位置。
     * null->0->1->2->3->4->5->6->7
     * 删除4到6
     * 去到前一个节点走4次(a)
     * 4到6删完需要删(4-2)+1次
     *
     * list1最多遍历到尾部也就是O(n)
     * list2遍历全部O(m)
     * O(n+m)
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode dummyNode=new ListNode();
        ListNode cur=dummyNode;
        ListNode pre=null;//指向a前面那个节点
        ListNode succ=null;//指向b后面那个
        dummyNode.next=list1;
        //找到要删除的前一个节点
        int n=a;
        int m=(b-a)+1;//删除几次
        while (n--!=0){
            cur=cur.next;
        }
        pre=cur;
        while (m--!=0){
            ListNode next=pre.next;
            pre.next=next.next;
            next.next=null;
        }
        //从pre开始连接list2 先记录尾部节点
        succ=pre.next;
        pre.next=list2;//连接list2
        //list2遍历到尾部
        while (list2!=null&&list2.next!=null){
            list2=list2.next;
        }
        list2.next=succ;
        return dummyNode.next;
    }
}
