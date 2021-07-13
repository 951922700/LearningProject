package QueueAndStack.Queue;

import java.util.Scanner;

/**
 * （洛谷P1551）亲戚
 *
 * 题目背景
 * 若某个家族人员过于庞大，要判断两个是否是亲戚，确实还很不容易，现在给出某个亲戚关系图，求任意给出的两个人是否具有亲戚关系。
 * 题目描述
 * 规定：x和y是亲戚，y和z是亲戚，那么x和z也是亲戚。如果x,y是亲戚，那么x的亲戚都是y的亲戚，y的亲戚也都是x的亲戚。
 * 输入格式
 * 第一行：三个整数n,m,p，（n<=5000,m<=5000,p<=5000），分别表示有n个人，m个亲戚关系，询问p对亲戚关系。
 * 以下m行：每行两个数Mi，Mj，1<=Mi，Mj<=N，表示Mi和Mj具有亲戚关系。
 * 接下来p行：每行两个数Pi，Pj，询问Pi和Pj是否具有亲戚关系。
 * 输出格式
 * P行，每行一个’Yes’或’No’。表示第i个询问的答案为“具有”或“不具有”亲戚关系。
 */
public class bingchaji01 {
    int parent[];
    int rank[];
    int n=0;
    int m=0;
    int p=0;
    public bingchaji01(int n,int m,int p){
        parent=new int[n+1];
        rank=new int[n+1];
        this.n=n;
        this.m=m;
        this.p=p;
    }

    //初始化父节点数组  深度全为1
    public void init(){

        for (int i=1;i<=this.n;i++){
            parent[i]=i;
            rank[i]=1;
        }
    }

    /**
     * 找父节点  沿途做路径压缩
     * 把找父节点 图中的节点直接指向父节点
     * @param x
     * @return
     */
    public int find(int x){
        return parent[x]==x?x:(parent[x]=find(parent[x]));
    }

    /**
     * 合并 同时做 秩
     * 小的深度父类指向大的深度
     */
    public void merge(int i,int j){
        int x=find(i);
        int y=find(j);

            //x根节点深度大于y根节点深度
            if (rank[x]>=rank[y]){
                parent[y]=x;
            }else{
                parent[x]=y;
            }
            if (rank[x]==rank[y]&&x!=y){
                //根节点深度相同且为不同节点  深度必定加1
                rank[x]++;
            }

    }

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        //10 个人  给出五对关系  询问三队
        int n=10,m=5,p=3;
        bingchaji01 bingchaji01=new bingchaji01(n,m,p);
        //初始化
        bingchaji01.init();

        for (int i=0;i<5;i++){
            int m1=input.nextInt(),m2=input.nextInt();
            bingchaji01.merge(m1,m2);
        }

        for (int i=0;i<p;i++){
            int p1=input.nextInt(),p2=input.nextInt();
            System.out.println(bingchaji01.find(p1)==bingchaji01.find(p2)?"Yes":"No");
        }
    }
}
