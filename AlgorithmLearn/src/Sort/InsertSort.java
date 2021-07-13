package Sort;

public class InsertSort {
    /**
     * 插入排序
     * 设第一个为有序
     * 后判断后一个数 如果是大于则不动
     *               如果小于则开始遍历
     * 稳定
     */
    static int[] insertSort(int a[]){
        for (int i=1;i<a.length;i++){
            int j;
            if (a[i]<a[i-1]){
                int temp=a[i];
                for (j=i-1;j>=0&&temp<a[j];j--){
                    a[j+1]=a[j];
                }
                //循环出来后的位置是小于temp的
                a[j+1]=temp;
            }
        }
        return a;
    }

    public static void main(String[] args) {

    }


}
