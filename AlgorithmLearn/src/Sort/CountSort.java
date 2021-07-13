package Sort;

import java.util.Arrays;

public class CountSort {
    /**
     * 计数排序
     * 时间O(n+k) k为最大值与最小值的差
     * 空间O(k)
     * 稳定
     *
     * 解决问题：
     * 学生成绩两个95 如何按照原来得顺序排序
     * 解决方法：
     * 1.变形数组
     * 2.逆序遍历原数组、同时对应变形数组-1
     *
     * 局限性：
     * 当数列最大最小值差距过大时，并不适用计数排序
     * 当数列元素不是整数，并不适用计数排序
     */
    public static int[] countSort(int[] array){
        //1.找到最大最小值
        int min=array[0];
        int max=array[0];
        for (int i=1;i<array.length;i++){
            if (array[i]>max)
                max=array[i];
            if (array[i]<min)
                min=array[i];
        }

        int countArray[]=new int[max-min+1];
        //2.计数
        for (int i=0;i<array.length;i++){
            countArray[array[i]-min]++;
        }
        //3.变形数组 让每一个变成前面数与自己的总和
        int sum=0;
        for (int i=0;i<countArray.length;i++){
            sum+=countArray[i];
            countArray[i]=sum;
        }

        int sortedArray[]=new int[array.length];//存放排序结果
        //4.逆序遍历原数组
        for (int i=array.length-1;i>=0;i--){
            //长度-名次 就是从大到小排
            sortedArray[countArray.length-countArray[array[i]-min]]=array[i];
            //sortedArray[countArray[array[i]-min]-1]=array[i];
            countArray[array[i]-min]--;
        }
        return sortedArray;
    }

    public static void main(String[] args) {
        int[] array = new int[] {95,94,91,98,99,90,99,93,91,92};
        int[] sortedArray = countSort(array);
        System.out.println(Arrays.toString(sortedArray));
    }


}
