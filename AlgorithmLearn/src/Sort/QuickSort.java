package Sort;

public class QuickSort {
    static int a[]=new int[]{3,5,23,6,7,2,56,9,10};
    /**
     * 快速排序
     * 平均（nlogn）
     * 最差 逆序的时候O（n^2）
     * 不稳定
     */
    static void quickSort(int left,int right){
        if (left>right)
            return ;
        int i=left,j=right;
        int temp=a[left];//基准
        while(i!=j){
            while(i<j&&a[j]>=temp)
                j--;
            while(i<j&&a[i]<=temp)
                i++;
            if (i<j){
                //如果i还是小于j的话  交换位置
                int cur=a[i];
                a[i]=a[j];
                a[j]=cur;
            }
        }
        //此时i，j的位置即基数应该在的位置 交换基数与i，j位置
        a[left]=a[i];
        a[i]=temp;
        quickSort(left,i-1);
        quickSort(j+1,right);
    }

    public static void main(String[] args) {
        copy(0,a.length-1);
        for (int cur:a) {
            System.out.print(" "+cur);
        }
    }

    public static void copy(int left,int right){
        if (left>right)
            return;
        int i=left,j=right;
        int temp=a[left];
        while(i!=j){
            while(i<j&&a[j]<=temp)
                j--;
            while(i<j&&a[i]>=temp)
                i++;
            if (i<j){
                int cur=a[i];
                a[i]=a[j];
                a[j]=cur;
            }
        }
        a[left]=a[i];
        a[i]=temp;
        copy(left,i-1);
        copy(i+1,right);

    }
}
