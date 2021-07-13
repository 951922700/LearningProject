package Sort;

public class BubbleSort {
    /**
     * 冒泡排序
     */

    /**
     * 未优化版
     * 时间稳定o(n^2)
     * 空间O（1）
     * n-1轮就能有序
     * 每轮要比较n-1-轮数次
     * 稳定
     */
    static int[] bubbleSort(int a[]){
        int len=a.length;
        for (int i=0;i<len-1;i++){
            for (int j=0;j<len-1-i;j++){
                if (a[j]>a[j+1]){
                    //从小到大排
                    int temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }
        return a;
    }

    /**
     * 利用一个标志优化排序
     *最好O(n)
     * @param a
     * @return
     */
    static int[] bubbleSortUp(int a[]){
        int len=a.length;
        boolean flag=false;//子循环是否发生变换   如果没有发生变换说明已经有序
        for (int i=0;i<len-1;i++){
            flag=false;//外循环要恢复默认  要不前面改动一次后面都是true根本没有优化
            for (int j=0;j<len-1-i;j++){
                if (a[j]>a[j+1]){
                    //从小到大排
                    int temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                    flag=true;//改了

                }
            }
            if (!flag)//没有发生变换
                break;
        }
        return a;
    }


    public static void main(String[] args) {
        int arr[] = { 5, 8, 6, 3, 9, 2, 1, 7 };
        arr=bubbleSortUp(arr);
        for (int cur:arr) {
            System.out.print(" "+cur);
        }
    }


}
