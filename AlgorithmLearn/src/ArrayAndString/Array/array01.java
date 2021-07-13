package ArrayAndString.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: intervals = [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *

 *
 *
 * 如果当前区间的左端点在数组 merged 中最后一个区间的右端点之后，那么它们不会重合，我们可以直接将这个区间加入数组 merged 的末尾；
 *
 * 否则，它们重合，我们需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，将其置为二者的较大值。
 *

 */
public class array01 {
    public static void main(String[] args) {

    }

    /*public int[][] merge(int[][] intervals) {
        //如果输入二维是空数组 返回一个空的二维数组
        if (intervals.length == 0) {
            return new int[0][2];
        }
        //对二维数组左端第一个元素进行排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];//前面减后面就是升序
            }
        });

        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            //一个区间的左端点和右端点
            int L = intervals[i][0], R = intervals[i][1];
            //当merged为空时直接把区间放进去，当新的左端点大于merged最后一个区间的右端点的时候  直接加进merged
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                //否则右端点用新的R和旧的R的最大值替代
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }*/


    public int[][] merge(int[][] intervals) {
        if(intervals.length==0) return new int[0][2];

        //排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        List<int[]> merged=new ArrayList<int[]>();

        for(int i=0;i<intervals.length;++i){
            int L=intervals[i][0],R=intervals[i][1];
            if(merged.size()==0||merged.get(merged.size()-1)[1]<L){
                merged.add(intervals[i]);
            }else{
                merged.get(merged.size()-1)[1]=Math.max(merged.get(merged.size()-1)[1],R);
            }
        }
        return merged.toArray(new int[0][] );
    }
}
