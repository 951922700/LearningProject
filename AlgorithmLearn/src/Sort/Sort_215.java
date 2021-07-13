package Sort;

public class Sort_215 {
    /**
     * 215. 数组中的第K个最大元素
     * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     *
     * 示例 1:
     *
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     * 示例 2:
     *
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     *
     * 随机数生成学习
     * random.nextInt(m)表示生成[0,m-1]之间的随机数，也就是说random.nextInt(m+1)，将生成[0,m]之间的随机整数
     * random.nextInt(max - min + 1) + min
     * Math.random()*(n+1-m)+m; //生成从m到n的随机整数[m,n]
     */
    public int findKthLargest(int[] nums, int k) {
        return quickSort(nums,0,nums.length-1,k);
    }
    public int quickSort(int [] a,int left,int right,int k){
        int i=left,j=right;
        int index=(int)Math.random()*(right-left+1)+left;

        //交换随机数
        int t=a[index];
        a[index]=a[left];
        a[left]=t;

        int temp=a[left];
        while (i!=j){
            while (i<j&&a[j]<=temp)
                j--;
            while (i<j&&a[i]>=temp)
                i++;
            if (i<j){
                int cur=a[i];
                a[i]=a[j];
                a[j]=cur;
            }
        }
        a[left]=a[i];
        a[i]=temp;
        if (i>k-1) return quickSort(a,left,i-1,k);
        if (i<k-1) return quickSort(a,i+1,right,k);
        return a[i];
    }

    public static void main(String[] args) {
        int[] nums=new int[]{5,2,4,1,3,6,0};
        Sort_215 s=new Sort_215();
        System.out.println(s.findKthLargest(nums, 4));
    }
}
