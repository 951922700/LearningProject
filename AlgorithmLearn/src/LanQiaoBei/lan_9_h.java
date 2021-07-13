package LanQiaoBei;

import java.util.*;

public class lan_9_h {
    /**
     * 日志统计
     *
     * 小明维护着一个程序员论坛。现在他收集了一份"点赞"日志，日志共有N行。其中每一行的格式是：
     *
     * ts id  
     *
     * 表示在ts时刻编号id的帖子收到一个"赞"。  
     *
     * 现在小明想统计有哪些帖子曾经是"热帖"。如果一个帖子曾在任意一个长度为D的时间段内收到不少于K个赞，小明就认为这个帖子曾是"热帖"。  
     *
     * 具体来说，如果存在某个时刻T满足该帖在[T, T+D)这段时间内(注意是左闭右开区间)收到不少于K个赞，该帖就曾是"热帖"。  
     *
     * 给定日志，请你帮助小明统计出所有曾是"热帖"的帖子编号。  
     *
     * 【输入格式】
     * 第一行包含三个整数N、D和K。  
     * 以下N行每行一条日志，包含两个整数ts和id。  
     *
     * 对于50%的数据，1 <= K <= N <= 1000  
     * 对于100%的数据，1 <= K <= N <= 100000 0 <= ts <= 100000 0 <= id <= 100000  
     *
     * 【输出格式】
     * 按从小到大的顺序输出热帖id。每个id一行。  
     *
     * 【输入样例】
     * 7 10 2  
     * 0 1  
     * 0 10    
     * 10 10  
     * 10 1  
     * 9 1
     * 100 3  
     * 100 3  
     *
     * 【输出样例】
     * 1  
     * 3  
     *
     * 尺取法
     */
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        int N=scan.nextInt();
        int D=scan.nextInt();//时间长度D
        int K=scan.nextInt();//K个赞
        Map<Integer,List<Integer>> map=new TreeMap<>();
        //key是id  value是点赞的时间序列
        for (int i=0;i<N;i++){
            int time=scan.nextInt();
            int id=scan.nextInt();
            if (map.containsKey(id)){
                map.get(id).add(time);
            }else{
                List<Integer> list=new ArrayList<>();
                list.add(time);
                map.put(id,list);
            }
        }
        //对时间序列进行排序  对map进行遍历
        Iterator<Map.Entry<Integer,List<Integer>>> entries=map.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry<Integer, List<Integer>> entry = entries.next();
            Collections.sort(entry.getValue());//对时间进行排序
            //用尺取法进行判断
            int sum=0;//赞数
            int size=entry.getValue().size();//列表长度
            for (int i=0;i<size;i++){
                int T=entry.getValue().get(i);//获取左边界的时间T   T+D应该>右边的时间
                int r;
                for (r=i+1;r<size;){
                    if (T+D>entry.getValue().get(r)){
                        //当前r位置依旧还小于T+D 继续向前进
                        r++;
                    }else {
                        //这个r已经不满足了
                        break;
                    }
                }
                //此时r之前i的数量即为点赞数量  比如 123456  i(1)指向2 r(4)指向5  则为234,收到三个赞   r-i
                sum=r-i;
                if (sum>=K){
                    System.out.println(entry.getKey());
                    break;//可以退出了  因为已经是热帖了
                }
            }
        }

    }


}
