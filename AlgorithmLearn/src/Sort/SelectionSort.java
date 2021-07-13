package Sort;

public class SelectionSort {
    /**
     * 选择排序
     * 时间O(n^2)
     * 空间O(1)
     * 不稳定
     */
    static int[] selectionSort(int a[]){
        for (int i=0;i<a.length;i++){
            //先找到应该处在i位置的值  也就是最小值（从小到大排）
            int index=i;
            for (int j=i+1;j<a.length;j++){
                if (a[j]<a[index])
                    index=j;
            }
            if (index==i){
                //若就在这个位置上跳出本次循环
                continue;
            }else{
                int temp=a[i];
                a[i]=a[index];
                a[index]=temp;
            }
        }
        return a;
    }


    public static void main(String[] args) {
        int arr[] = { 5, 8, 6, 3, 9, 2, 1, 7 };
        arr=selectionSort(arr);
        for (int cur:arr) {
            System.out.print(" "+cur);
        }
    }

}
