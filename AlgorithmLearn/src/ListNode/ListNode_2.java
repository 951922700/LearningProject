package ListNode;

public class ListNode_2 {
    /**
     * 2. 两数相加
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * <p>
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
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
        int temp=0;//存进位
        ListNode ans=new ListNode();
        ListNode pre=ans;
        int left;
        int right;
        /**
         * 2 4 3
         * 5 6 4
         * 7 0 8
         */
        while (true){
            if (l1==null){
                left=0;
            }else{
                left=l1.val;
            }
            if (l2==null){
                right=0;
            }else{
                right=l2.val;
            }

            int result=0;
            if (temp!=0){
                //有进位
                result=left+right+temp;
                temp=0;//进位置0
            }else{
                //无进位
                result=left+right;
            }
            //小于10位没有进位
            if (result<10){
                pre.val=result;
               /* pre.next=new ListNode();
                pre=pre.next;*/
            }else{
                //有进位 放入个位数
                temp=1;
                pre.val=result%10;
                /* pre.next=new ListNode();
                pre=pre.next;*/
            }
            //不为null指向下一个数 为null让l1为null方便操作
            if (l1!=null&&l1.next!=null)
                l1=l1.next;
            else
                l1=null;

            if (l2!=null&&l2.next!=null)
                l2=l2.next;
            else
                l2=null;
            //此时下一个值节点是必定存在的 创建一个next    如果不判断直接创建的话可能答案最后面会多出一个0
            if (!(l1==null&&l2==null)){
                pre.next=new ListNode();
                pre=pre.next;
            }else{
                //此时两个链表都为null了结束循环  同时也要判断是否有进位
                if (temp!=0){
                    pre.next=new ListNode();
                    pre=pre.next;
                    pre.val=temp;
                }
                break;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        ListNode_2 node=new ListNode_2();
        ListNode ls=new ListNode(2);
        ListNode ls1=new ListNode(4);
        ListNode ls2=new ListNode(3);
        ls.next=ls1;
        ls1.next=ls2;
        ListNode ln=new ListNode(5);
        ListNode ln1=new ListNode(6);
        ListNode ln2=new ListNode(4);
        ln.next=ln1;
        ln1.next=ln2;
        node.addTwoNumbers(ls,ln);
    }
}
