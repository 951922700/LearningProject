package ListNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ListNode_offer06 {
    /**
     * 剑指 Offer 06. 从尾到头打印链表
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     *
     *
     *
     * 示例 1：
     *
     * 输入：head = [1,3,2]
     * 输出：[2,3,1]
     */
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
    public int[] reversePrint(ListNode head) {
        Deque<Integer> stack=new ArrayDeque<>();
        int n=0;
        while (head!=null){
            n++;
            stack.push(head.val);
            head=head.next;
        }
        int[] ans=new int[n];
        n=0;
        while (!stack.isEmpty()){
            ans[n++]=stack.pop();
        }
        return ans;
    }
}
