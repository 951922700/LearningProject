package ListNode;

public class ListNode_92 {
    /**
     * 92. 反转链表 II
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
     * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     */
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    /**
     * 非一次遍历
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        //创建一个虚拟节点，因为有可能是反转从头到尾  减少很多判断
        ListNode dummyNode=new ListNode(-1);
        dummyNode.next=head;
        //找到反转区域的前一个节点
        ListNode pre=dummyNode;
        ListNode rightNode=dummyNode;
        //移动到left-1的位置
        for (int i=0;i<left-1;i++)
            pre=pre.next;
        //找到最后一个节点
        for (int i=0;i<right;i++)
            rightNode=rightNode.next;
        //保存right+1位置以后的节点位置 以及leftnode的位置
        ListNode succ=rightNode.next;
        ListNode leftNode=pre.next;
        rightNode.next=null;//切断方便递归逆转
        pre.next=reverseList(pre.next);
        leftNode.next=succ;//左边被反过来了
        return dummyNode.next;
    }
    public ListNode reverseList(ListNode head) {
        if (head==null||head.next==null)
            return head;
        ListNode p=reverseList(head.next);
        head.next.next=head;
        head.next=null;
        return p;
    }

    /**
     * 一次遍历  头插法
     * @param head
     * @param left
     * @param right
     * @return
     *-1->1->2->3->4->5    2 3 4转换
     * 保持指针指向2不变，2虽然一直在移动但是cur指针不变
     * cur的next是要转换的点
     * 所以转换次数是right-left  也就是总长度-1
     */
    public ListNode reverseBetween1(ListNode head, int left, int right) {
        ListNode dummyNode=new ListNode(-1);
        dummyNode.next=head;
        ListNode pre=dummyNode;//永远指向转换区域的前一个节点
        //理解为从虚拟节点开始 需要跳几次到前一个节点  很明显left-1次
        for (int i=0;i<left-1;i++)
            pre=pre.next;
        ListNode cur=pre.next;//实际上上一直指向原始链表的第一个要反转的那个节点  但是那个节点会一直移动
        ListNode next;//指向cur的下一个节点  这个节点是要变到开头的节点
        for (int i=0;i<right-left;i++){
           next=cur.next;//首先指向cur的下一个节点
           cur.next=next.next;
           next.next=pre.next;
           pre.next=next;
        }
        return dummyNode.next;
        //总体是一次遍历
    }
}
