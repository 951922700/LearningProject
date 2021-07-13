package Sort;

public class MergeSort {
    static int a[]=new int[]{3,5,23,6,7,2,56,9,10};
    /**
     * 归并排序
     * 时间O(nLogn)
     * 空间O(n)
     * 稳定
     */
    public static void mergeSort(int L,int R){
        if (L>=R)
            return;
        int mid=L+(R-L)/2;
        mergeSort(L,mid);
        mergeSort(mid+1,R);
        merge(L,mid,R);
    }

    public static void merge(int L,int mid,int R){
        int temp[]=new int[R-L+1];//临时数组存放排序值
        int p1=L;
        int p2=mid+1;
        int t=0;
        while(p1<=mid&&p2<=R){
            if (a[p1]<=a[p2])
                temp[t++]=a[p1++];
            else
                temp[t++]=a[p2++];
        }

        while(p1<=mid)
            temp[t++]=a[p1++];
        while(p2<=R)
            temp[t++]=a[p2++];

        for (int cur:temp) {
            a[L++]=cur;
        }
    }

    public static void main(String[] args) {
        mergeSort(0,a.length-1);
        for (int cur:a) {
            System.out.print(" "+cur);
        }
    }

}
