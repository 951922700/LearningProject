package Sort;

public class Sort_offer45 {
    /**
     * 剑指 Offer 45. 把数组排成最小的数
     * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     *
     *
     *
     * 示例 1:
     *
     * 输入: [10,2]
     * 输出: "102"
     * 示例 2:
     *
     * 输入: [3,30,34,5,9]
     * 输出: "3033459"
     *
     *
     * 定义 x+y>y+x  为x>y
     */
    public String minNumber(int[] nums) {
        String strs[]=new String[nums.length];
        for (int i=0;i<nums.length;i++) {
            strs[i]=String.valueOf(nums[i]);
        }
        quickSort(strs,0,strs.length-1);
        StringBuilder sb=new StringBuilder();
        for (String cur:
             strs) {
            sb.append(cur);
        }
        return sb.toString();
    }

    public void quickSort(String[] strs, int left, int right){
        if (left>=right)
            return;
        int i=left;
        int j=right;
        String temp=strs[left];
        while(i!=j){
            while (i<j&&(strs[j]+temp).compareTo(temp+strs[j])>=0)
                j--;

            while (i<j&&(strs[i]+temp).compareTo(temp+strs[i])<=0)
                i++;

            if (i<j){
                String cur=strs[i];
                strs[i]=strs[j];
                strs[j]=cur;
            }
        }
        strs[left]=strs[i];
        strs[i]=temp;
        quickSort(strs,left,i-1);
        quickSort(strs,i+1,right);
    }
}
