package BinarySearn;

public class binary08_1095 {
    /**
     * 1095. 山脉数组中查找目标值
     * （这是一个 交互式问题 ）
     *
     * 给你一个 山脉数组 mountainArr，请你返回能够使得 mountainArr.get(index) 等于 target 最小 的下标 index 值。
     *
     * 如果不存在这样的下标 index，就请返回 -1。
     *
     *
     *
     * 何为山脉数组？如果数组 A 是一个山脉数组的话，那它满足如下条件：
     *
     * 首先，A.length >= 3
     *
     * 其次，在 0 < i < A.length - 1 条件下，存在 i 使得：
     *
     * A[0] < A[1] < ... A[i-1] < A[i]
     * A[i] > A[i+1] > ... > A[A.length - 1]
     *
     *
     * 你将 不能直接访问该山脉数组，必须通过 MountainArray 接口来获取数据：
     *
     * MountainArray.get(k) - 会返回数组中索引为k 的元素（下标从 0 开始）
     * MountainArray.length() - 会返回该数组的长度
     */
    class MountainArray {
        public int get(int index) {return 1;}
        public int length() {return 2;}
    }

    //找峰顶   左侧找值 右侧找值
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int topIndex=findMountainTop(mountainArr);
        int index=findMountainTargetLeft(mountainArr,topIndex,target);
        if (index!=-1)
            return index;
        return findMountainTargetRight(mountainArr,topIndex,target);
    }

    //返回顶峰索引
    public int findMountainTop(MountainArray mountainArr){
        int len=mountainArr.length();
        int left=0,right=len-1;

        while(left<right){
            int mid=left+(right-left)/2;
            if (mountainArr.get(mid)<mountainArr.get(mid+1)){
                left=mid+1;
            }else{
                right=mid;
            }
        }
        return left;
    }

    //找左区间是否有target
    public int findMountainTargetLeft(MountainArray mountainArr,int topIndex,int target){
        int left=0,right=topIndex;
        while(left<right){
            int mid=left+(right-left)/2;
            if (mountainArr.get(mid)<target){
                left=mid+1;
            }else{
                right=mid;
            }
        }
        if (mountainArr.get(left)==target)
            return left;
        return -1;
    }

    //找右区间(降序的)是否有target
    public int findMountainTargetRight(MountainArray mountainArr,int topIndex,int target){
        int left=topIndex,right=mountainArr.length()-1;
        while(left<right){
            int mid=left+(right-left)/2;
            if (mountainArr.get(mid)>target){
                left=mid+1;
            }else{
                right=mid;
            }
        }
        if (mountainArr.get(left)==target)
            return left;
        return -1;
    }
}
