package Sort;

public class Sort_75 {
    /**
     * 75. 颜色分类
     * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     *
     * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [2,0,2,1,1,0]
     * 输出：[0,0,1,1,2,2]
     * 示例 2：
     *
     * 输入：nums = [2,0,1]
     * 输出：[0,1,2]
     * 示例 3：
     *
     * 输入：nums = [0]
     * 输出：[0]
     * 示例 4：
     *
     * 输入：nums = [1]
     * 输出：[1]
     *
     * todo 要学习快速排序的随机数、二路、三路
     * 这里先用计数排序通过
     */
    public void sortColors(int[] nums) {
        int bucket[]=new int[3];
        for (int num:
             nums) {
            bucket[num]++;
        }

        int j=0;
        for (int i=0;i<bucket.length;i++){

            nums[j]=i;
        }
        int i=0;
        while (i<3){
            if (bucket[i]!=0){
                bucket[i]--;
                nums[j++]=i;
            }
            else
                i++;
        }
    }
    public void sortColors1(int[] nums) {
        int p0=0,p1=0;//p0表示交换0  p1表示交换1
        for (int i = 0; i <nums.length ; i++) {
            if (nums[i]==1){
                int temp=nums[p1];
                nums[p1]=nums[i];
                nums[i]=temp;
                p1++;
            }else if (nums[i]==0){
                int temp=nums[p0];
                nums[p0]=nums[i];
                nums[i]=temp;
                if (p0<p1){
                    int t=nums[p1];
                    nums[p1]=nums[i];
                    nums[i]=t;
                }
                p0++;
                p1++;
            }
        }
    }
}
