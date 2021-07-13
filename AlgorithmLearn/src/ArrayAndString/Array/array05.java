package ArrayAndString.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 杨辉三角 II
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 */
public class array05 {
    //可以说是动态规划  因为用list存储了每一行的状态
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists=new ArrayList<>();
        for (int i=1;i<=numRows;i++){
            List<Integer> list=new ArrayList<>();
            for (int j=0;j<i;j++){
                if (j==0||j==i-1) list.add(1);//头尾放1
                else {
                    List<Integer> temp=lists.get(i-2);//获取上一行的元素
                    list.add(temp.get(j-1)+temp.get(j));//左右上方和
                }
            }
            lists.add(list);
        }
        return lists;
    }

    //这样空间用的多了
/*    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> lists=new ArrayList<>();
        for (int i=1;i<=rowIndex+1;i++){
            List<Integer> list=new ArrayList<>();
            for (int j=0;j<i;j++){
                if (j==0||j==i-1) list.add(1);//头尾放1
                else {
                    List<Integer> temp=lists.get(i-2);//获取上一行的元素
                    list.add(temp.get(j-1)+temp.get(j));//左右上方和
                }
            }
            lists.add(list);
        }
        return lists.get(rowIndex);
    }*/

    //不存放所有行  减少空间内存使用
    public List<Integer> getRow(int rowIndex) {
        List<Integer> lists=new ArrayList<>();//存放上一行元素
        for (int i=1;i<=rowIndex+1;i++){
            List<Integer> list=new ArrayList<>();
            for (int j=0;j<i;j++){
                if (j==0||j==i-1) list.add(1);//头尾放1
                else {
                    list.add(lists.get(j-1)+lists.get(j));//左右上方和
                }
            }
            lists=list;
        }
        return lists;
    }


}
