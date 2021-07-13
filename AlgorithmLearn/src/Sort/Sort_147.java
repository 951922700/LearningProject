package Sort;

public class Sort_147 {
    /**
     * 147. 对链表进行插入排序
     * 对链表进行插入排序。
     *
     *
     * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
     * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
     *
     *
     *
     * 插入排序算法：
     *
     * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
     * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
     * 重复直到所有输入数据插入完为止。
     *
     *
     * 示例 1：
     *
     * 输入: 4->2->1->3
     * 输出: 1->2->3->4
     * 示例 2：
     *
     * 输入: -1->5->3->4->0
     * 输出: -1->0->3->4->5
     */

    public ListNode insertionSortList(ListNode head) {
        ListNode pre=new ListNode(head.val);//存放结果的头指针
        head=head.next;
        ListNode result=pre;//指向结果链表的最后一位
        while(head!=null){
            ListNode cur=new ListNode(head.val);//要放入的节点
            if (head.val<result.val){
                //遍历结果链表中找到第一个大于输入val的位置的前一个位置  2        134
                ListNode temp=pre;//用于遍历的指针
                //先判断是不是应该放在最前面
                if (temp.val>head.val){
                    cur.next=pre;
                    pre=cur;
                }else{
                    while (temp!=null){
                        if (temp.next!=null&&temp.next.val>head.val){
                            //将head的val放到当前temp前面
                            cur.next=temp.next;
                            temp.next=cur;
                            break;
                        }
                        temp=temp.next;
                    }
                }

            }else{
                //head.val<result.val  应该放在链表尾部
                result.next=cur;
                result=cur;
            }

            head=head.next;
        }
        return pre;
    }



     public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    //插入排序
    public ListNode insertionSortList1(ListNode head){
        if (head==null)
            return null;
        ListNode dummyNode=new ListNode();//哨兵节点
        dummyNode.next=head;
        ListNode cur=head.next;//从第二点开始插入
        ListNode lastSorted=head;//为已排序的最后一个节点
        while (cur!=null){
            //判断cur与最后一个排序节点的大小情况
            if (cur.val>=lastSorted.val){
                //大于或等于最后一个数 不用排   更新last指针
                lastSorted=cur;
                cur=cur.next;
            }else{
                //小于  需要从头节点开始找到第一个.next大于cur的节点 1 2 4   3
                ListNode temp=dummyNode;
                while (temp.next!=null&&temp.next.val<=cur.val){
                    temp=temp.next;
                }
                //循环出来此时temp.next.val>cur lastSorted不用更新
                ListNode tempCur=cur;//
                cur=cur.next;
                lastSorted.next=tempCur.next;
                tempCur.next=temp.next;
                temp.next=tempCur;
            }
        }
        return dummyNode.next;
    }
}
