package Sort;

import java.util.*;

public class BucketSort {
    /**
     * 桶排序  n个元素 m个桶  平均每个桶n/m个元素
     * 时间 如果n=m O(n)  其他OnLog(n)
     * 空间 O(m+n)
     * 稳定性取决于桶内所用的排序
     *
     *
     * 第一步求数列最大最小值，运算量为n。
     * 第二步创建空桶，运算量为m。
     * 第三步遍历原始数列，运算量为n。
     * 第四步在每个桶内部做排序，由于使用了O（nlogn）的排序算法，所以运算量为 n/m * log(n/m ) * m。
     * 第五步输出排序数列，运算量为n。
     * 加起来，总的运算量为 3n+m+ n/m * log(n/m ) * m = 3n+m+n(logn-logm) 。
     * 去掉系数，时间复杂度为：
     * O(n+m+n(logn-logm)）
     */
    public static int[] bucketSort(int a[]){
        //1.找最大最小值
        int max=a[0];
        int min=a[0];
        for (int i=0;i<a.length;i++){
            if (a[i]>max)
                max=a[i];
            if (a[i]<min)
                min=a[i];
        }
        //2.创建桶 (最大-最小)/长度+1    区间长度=（最大-最小）/(桶数量-1)
        int bucketNum=(max-min)/a.length+1;
        List<List<Integer>> bucket=new ArrayList<>(bucketNum);
        for (int i=0;i<bucketNum;i++)
            bucket.add(new ArrayList<Integer>());

        //3.将元素放入桶中
        for (int i=0;i<a.length;i++){
            int index=(a[i]-min)/a.length;
            bucket.get(index).add(a[i]);
        }

        //4.各自排序
        for (int i=0;i<bucket.size();i++)
            Collections.sort(bucket.get(i));

        //5.赋值结果
        int index=0;
        for (int i=0;i<bucket.size();i++){
            for (int j=0;j<bucket.get(i).size();j++){
                a[index++]=bucket.get(i).get(j);
            }
        }
        return a;
    }

    public static void main(String[] args) {
        int[] array = new int[] {95,94,91,98,99,90,99,93,91,92};
        int[] sortedArray = bucketSort(array);
        System.out.println(Arrays.toString(sortedArray));
    }


}
