package ListNode;

public class ListNode_148 {
    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    /**
     * 148. 排序链表
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     *
     * 进阶：
     *
     * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
     *
     * todo  自底向上解法
     */
    //归并排序
    public static ListNode sortList(ListNode head) {
        if (head==null||head.next==null)
            return head;
        return mergeSort(head);
    }

    public static ListNode mergeSort(ListNode left){
        //当只有一个节点 或者 没有的时候 递归结束
        if (left==null||left.next==null)
            return left;
        //找偏向左边中点
        ListNode slow=left,fast=left;
        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode pre=slow.next;//右边链表头节点
        slow.next=null;//断开链表
        ListNode head1=mergeSort(left);
        ListNode head2=mergeSort(pre);
        return merge(head1,head2);
    }

    public static ListNode merge(ListNode l1,ListNode l2){
        ListNode dummyHead=new ListNode();
        ListNode cur=dummyHead;
        while (l1!=null&&l2!=null){
            if (l1.val>=l2.val){
                //放l2
                cur.next=l2;
                l2=l2.next;
                cur=cur.next;
            }else{
                //放l1
                cur.next=l1;
                l1=l1.next;
                cur=cur.next;
            }
        }
        //长度相差为1
        if (l1!=null)
            cur.next=l1;
        else
            cur.next=l2;
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode head4=new ListNode(4);
        ListNode head2=new ListNode(2);
        ListNode head1=new ListNode(1);
        ListNode head3=new ListNode(3);
        head4.next=head2;
        head2.next=head1;
        head1.next=head3;
        sortList(head4);
    }
}
