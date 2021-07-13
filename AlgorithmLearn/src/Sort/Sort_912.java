package Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sort_912 {
    /**
     * 升序排序
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        quickSort(nums,0,nums.length-1);
        return nums;
    }

    public int[] bucketSort(int array[]){
        int Max=array[0];
        int Min=array[0];
        for (int i=0;i<array.length;i++){
            Max=Math.max(Max,array[i]);
            Min=Math.min(Min,array[i]);
        }

        int bucketNum=(Max-Min)/array.length+1;
        List<List<Integer>> bucket=new ArrayList<>();
        for (int i=0;i<bucketNum;i++){
            bucket.add(new ArrayList<>());
        }

        //放元素
        for (int i=0;i<array.length;i++){
            int index=(array[i]-Min)/array.length;
            bucket.get(index).add(array[i]);
        }

        for (int i=0;i<bucket.size();i++)
            Collections.sort(bucket.get(i));

        int index=0;
        for (int i=0;i<bucket.size();i++){
            for (int j=0;j<bucket.get(i).size();j++){
                array[index++]=bucket.get(i).get(j);
            }
        }
        return array;
    }

    public int[] countSort(int a[]){
        int max=a[0];
        int min=a[0];

        for (int i=1;i<a.length;i++){
            max=Math.max(max,a[i]);
            min=Math.min(min,a[i]);
        }

        int count[]=new int[max-min+1];
        for (int i=0;i<a.length;i++){
            count[a[i]-min]++;
        }

        int sum=0;
        for (int i=0;i<count.length;i++){
            sum+=count[i];
            count[i]=sum;
        }

        int sortedArray[]=new int[a.length];
        for (int j=a.length-1;j>=0;j--){
            sortedArray[count[a[j]-min]-1]=a[j];
            count[a[j]-min]--;
        }
        return sortedArray;
    }

    public void mergeSort(int a[],int left,int right){
        if (left>=right)
            return;
        int mid=left+(right-left)/2;
        mergeSort(a,left,mid);
        mergeSort(a,mid+1,right);
        merge(a,left,mid,right);
    }

    public void merge(int a[],int left,int mid,int right){
        //left~mid是一个有序数组  mid+1~right是另一个有序数组
        int temp[]=new int[right-left+1];
        int p1=left;
        int p2=mid+1;
        int t=0;
        while(p1<=mid&&p2<=right){

            if (a[p1]<a[p2])
                temp[t++]=a[p1++];
            else
                temp[t++]=a[p2++];
        }

        while(p1<=mid)
            temp[t++]=a[p1++];
        while (p2<=right)
            temp[t++]=a[p2++];

        for (int cur:temp) {
            a[left++]=cur;
        }
    }

    public void quickSort(int[] a,int left,int right){
        if (left>right)
            return;
        int i=left,j=right;

        int temp=a[left];
        while(i!=j){
            while (i<j&&a[j]>=temp)
                j--;
            while(i<j&&a[i]<=temp)
                i++;
            if (i<j){
                int cur=a[i];
                a[i]=a[j];
                a[j]=cur;
            }
        }
        a[left]=a[i];
        a[i]=temp;
        quickSort(a,left,i-1);
        quickSort(a,i+1,right);
    }

    public int[] insertSort(int a[]){
        for (int i=1;i<a.length;i++){
            int j;
            if (a[i]<a[i-1]){
                int temp=a[i];
                for ( j=i-1;j>=0&&temp<a[j];j--){
                    a[j+1]=a[j];
                }
                a[j+1]=temp;
            }
        }
        return a;
    }

    public int[] bubbleSort(int a[]){
        boolean flag=false;
        for (int i=0;i<a.length-1;i++){
            flag=false;
            for (int j=0;j<a.length-i-1;j++){
                if (a[j]>a[j+1]){
                    int temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                    flag=true;
                }
            }
            if (!flag)
                break;
        }
        return a;
    }

    public int[] SelectionSort(int a[]){
        for (int i=0;i<a.length;i++){
            int index=i;
            for (int j=i+1;j<a.length;j++){
                if (a[j]<a[index])
                    index=j;
            }
            if (index==i)
                continue;
            else{
                int temp=a[index];
                a[index]=a[i];
                a[i]=temp;
            }
        }
        return a;
    }
}
