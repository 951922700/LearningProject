package ListNode;

public class ListNode_23 {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    /**
     * 23. 合并K个升序链表
     * 给你一个链表数组，每个链表都已经按升序排列。
     *
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists,0,lists.length-1);
    }

    public ListNode merge(ListNode[] lists,int l,int r){
        if (l==r)
            return lists[l];
        if (l>r)
            return null;
        int mid=l+(r-l)/2;
        return mergeTwoLists(merge(lists,l,mid),merge(lists,mid+1,r));
    }
    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyNode=new ListNode(-1);
        ListNode cur=dummyNode;
        while (l1!=null&&l2!=null){
            if (l1.val>=l2.val){
                //放l2
                cur.next=l2;
                ListNode next=l2.next;//获取下一个节点
                l2.next=null;//取消当前节点的连接
                l2=next;//指向下一个节点
                cur=cur.next;
            }else{
                cur.next=l1;
                ListNode next=l1.next;//获取下一个节点
                l1.next=null;//取消当前节点的连接
                l1=next;//指向下一个节点
                cur=cur.next;
            }
        }
        while (l1!=null){
            cur.next=l1;
            ListNode next=l1.next;//获取下一个节点
            l1.next=null;//取消当前节点的连接
            l1=next;//指向下一个节点
            cur=cur.next;
        }
        while (l2!=null){
            cur.next=l2;
            ListNode next=l2.next;//获取下一个节点
            l2.next=null;//取消当前节点的连接
            l2=next;//指向下一个节点
            cur=cur.next;
        }
        return dummyNode.next;
    }
}
