package ListNode;

import java.util.ArrayList;
import java.util.List;

public class ListNode_234 {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    /**
     * 234. 回文链表
     * 请判断一个链表是否为回文链表。
     *
     * 示例 1:
     *
     * 输入: 1->2
     * 输出: false
     * 示例 2:
     *
     * 输入: 1->2->2->1
     * 输出: true
     * 时间O(n)
     */
    public boolean isPalindrome(ListNode head) {
        ListNode pre=head;
        List<Integer> nums=new ArrayList<>();
        while (pre!=null){
            nums.add(pre.val);
            pre=pre.next;
        }
        int i=0,j=nums.size()-1;
        while (i<j){
            if (nums.get(i)!=nums.get(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
    /**
     * 如何做到时间O(n) 空间O(1)
     * 1.找中点
     * 2.反转
     * 3.对比
     * 4.恢复
     */
}
