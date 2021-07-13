package Sort;

import java.util.Arrays;

public class Sort_offer40 {
    /**
     * 剑指 Offer 40. 最小的k个数
     * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     *
     *
     *
     * 示例 1：
     *
     * 输入：arr = [3,2,1], k = 2
     * 输出：[1,2] 或者 [2,1]
     * 示例 2：
     *
     * 输入：arr = [0,1,2,1], k = 1
     * 输出：[0]
     *
     * TOP K问题
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k>=arr.length) return arr;
        return quickSort(arr,0,arr.length-1,k);
    }

    public int[] quickSort(int[] arr,int left,int right,int k){
        //123  k=2 找到基准索引=k的位置  此时位置的左侧全部数都是前k个最小值
        int i=left,j=right;
        int temp=arr[left];
        while(i!=j){
            while (i<j&&arr[j]>=temp)
                j--;
            while (i<j&&arr[i]<=temp)
                i++;
            if (i<j){
                int cur=arr[i];
                arr[i]=arr[j];
                arr[j]=cur;
            }
        }
        arr[left]=arr[i];
        arr[i]=temp;
        //此时i为基准数所在位置
        if (i<k)// 此时要找的基准数应该在右侧
            return quickSort(arr,i+1,right,k);
        if (i>k)//此时要找的基准数在左侧
            return quickSort(arr,left,i-1,k);
        //i==k
        return Arrays.copyOf(arr,k);//k为创建的数组长度
    }
}
