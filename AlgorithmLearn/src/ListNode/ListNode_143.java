package ListNode;

import java.util.ArrayList;
import java.util.List;

public class ListNode_143 {
    /**
     * 143. 重排链表
     * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
     * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
     *
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * 示例 1:
     *
     * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
     * 示例 2:
     *
     * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
     */
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    /**
     * 方法一：线性表
     * 放入一个list中 然后重组
     * @param head
     */
    public void reorderList1(ListNode head) {
        if (head==null)
            return;
        ListNode node=head;
        List<ListNode> list=new ArrayList<>();
        while (node!=null){
            list.add(node);
            node=node.next;
        }
        int i=0,j=list.size()-1;
        //用1->2->3->4->5  1->2->3->4来分析 i==j退出以及  为什么最后next要null
        while (i<j){
            list.get(i).next=list.get(j);
            i++;
            if (i==j)
                break;
            list.get(j).next=list.get(i);
            j--;
        }
        //记得把最后一个值的next置null 不然会有环
        list.get(i).next=null;
    }

    /**
     * 方法二：找中点  将右半部分链表逆转  合并链表
     */
    public void reorderList(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;
        //此中点偏向左边
        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        //此时slow指向偏向左边的中点 将他和右半部分断开  无论是奇数偶数两边长度顶多差1
        ListNode l1=head;
        ListNode l2=slow.next;//右链表开头
        slow.next=null;
        l2=reverseList(l2);
        mergeList(l1,l2);
    }
    //反转
    public ListNode reverseList(ListNode head) {
        ListNode pre=null;
        ListNode cur=head;
        while (cur!=null){
            ListNode next=cur.next;//保存next
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return pre;
    }

    /**
     * 1->2->3
     * 6->5
     */
    public void mergeList(ListNode l1,ListNode l2){
        ListNode l1_temp;
        ListNode l2_temp;
        while (l1!=null&&l2!=null){
            l1_temp=l1.next;
            l2_temp=l2.next;

            l1.next=l2;
            l1=l1_temp;//移动到下一个位置

            l2.next=l1;
            l2=l2_temp;
        }
    }
}
