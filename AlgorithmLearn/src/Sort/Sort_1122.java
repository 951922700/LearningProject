package Sort;

public class Sort_1122 {
    /**
     * 1122. 数组的相对排序
     * 给你两个数组，arr1 和 arr2，
     *
     * arr2 中的元素各不相同
     * arr2 中的每个元素都出现在 arr1 中
     * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
     *
     *
     *
     * 示例：
     *
     * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
     * 输出：[2,2,2,1,4,3,3,9,6,7,19]
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        //注意到数组大小才1000  就不用max-min了
        int Max=arr1[0];
        for (int num:
             arr1) {
            Max=Math.max(Max,num);
        }
        int []frequency=new int[Max+1];
        for (int num:
             arr1) {
            frequency[num]++;
        }

        int[] ans=new int[arr1.length];
        int index=0;
        for (int num:
             arr2) {
            for (int i=0;i<frequency[num];i++){
                ans[index++]=num;
            }
            frequency[num]=0;
        }

        //frequency中还有些arr2没有的元素  遍历一遍
        for (int i=0;i<=Max;i++){
            for (int j=0;j<frequency[i];j++){
                ans[index++]=i;
            }
        }
        return ans;
    }
}
