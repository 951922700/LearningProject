package ListNode;

public class ListNode_725 {
   public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
    /**
     * 725. 分隔链表
     * 给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。
     *
     * 每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。
     *
     * 这k个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。
     *
     * 返回一个符合上述规则的链表的列表。
     *
     * 举例： 1->2->3->4, k = 5 // 5 结果 [ [1], [2], [3], [4], null ]
     */
    /**
     *思路：N/k表示每个分几个  N%K表示有多少个需要多一个节点
     * 上面k=3
     * 12  3  4   4/3=1  4%3=1  第1个需要多一个节点
     */
    public static ListNode[] splitListToParts(ListNode root, int k) {
        ListNode cur=root;
        int N=0;
        while (cur!=null){
            N++;
            cur=cur.next;
        }
        int width=N/k,num=N%k;
        cur=root;
        ListNode[] listNodes=new ListNode[k];
        for (int i=0;i<k;i++){
            int time=0;
            if (num!=0){
                time=width+1;
                num--;
            }else{
                time=width;
            }
            if (time!=0){
                ListNode temp=new ListNode(-1);
                ListNode temphead=temp;
                while (time--!=0){
                    temp.next=cur;
                    cur=cur.next;
                    temp.next.next=null;
                    temp=temp.next;
                }
                listNodes[i]=temphead.next;
            }
        }
        return listNodes;
    }

    public static void main(String[] args) {
        ListNode root=new ListNode(1);
        ListNode root1=new ListNode(2);
        ListNode root2=new ListNode(3);
        root.next=root1;
        root1.next=root2;
        splitListToParts(root,5);
    }
}
