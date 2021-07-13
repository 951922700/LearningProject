package ListNode;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;

public class ListNode_445 {
    /**
     * 445. 两数相加 II
     * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
     *
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     *
     *
     *
     * 进阶：
     *
     * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
     *
     * 重点：要学会biginteger的方法
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() { }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
       ListNode ans=new ListNode();
       ListNode pre=ans;
       Deque<Integer> stack1=new ArrayDeque<>();
       Deque<Integer> stack2=new ArrayDeque<>();
       //可以不用第三个栈 用头插法
       Deque<Integer> stack3=new ArrayDeque<>();
       while (l1!=null){
           stack1.push(l1.val);
           l1=l1.next;
       }
       while (l2!=null){
           stack2.push(l2.val);
           l2=l2.next;
       }
       int temp=0;//进位
        int left=0,right=0;
       while (!(stack1.isEmpty()&&stack2.isEmpty())){
           if (stack1.isEmpty()){
               left=0;
           }else{
               left=stack1.pop();
           }
           if (stack2.isEmpty()){
               right=0;
           }else{
               right=stack2.pop();
           }
           int result;
           if (temp!=0){
               result=left+right+temp;
               temp=0;
           }
           else
               result=left+right;

           if (result<10){
               stack3.push(result);
           }else{
               //有进位
               temp=1;
               stack3.push(result%10);
           }
       }
       //循环出来看看temp
        if (temp!=0)
            stack3.push(temp);

        while (!stack3.isEmpty()){
            pre.val=stack3.pop();
            if (!stack3.isEmpty()){
                pre.next=new ListNode();
                pre=pre.next;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        ListNode_445 node=new ListNode_445();
        ListNode ls=new ListNode(7);
        ListNode ls1=new ListNode(2);
        ListNode ls2=new ListNode(4);
        ListNode ls3=new ListNode(3);
        ls.next=ls1;
        ls1.next=ls2;
        ls2.next=ls3;
        ListNode ln=new ListNode(5);
        ListNode ln1=new ListNode(6);
        ListNode ln2=new ListNode(4);
        ln.next=ln1;
        ln1.next=ln2;
        node.addTwoNumbers(ls,ln);
    }
}
