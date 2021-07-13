package LanQiaoBei;

import java.util.Scanner;

public class lan_10_h {
    /**
     * 【问题描述】
     * 小明正在分析一本小说中的人物相关性。他想知道在小说中 Alice 和 Bob 有多少次同时出现。
     * 更准确的说，小明定义 Alice 和 Bob“同时出现”的意思是：在小说文本 中 Alice 和 Bob 之间不超过 K 个字符。
     * 例如以下文本： This is a story about Alice and Bob. Alice wants to send a private message to Bob.
     * 假设 K = 20，则 Alice 和 Bob 同时出现了 2 次，分别是”Alice and Bob” 和”Bob. Alice”。
     * 前者 Alice 和 Bob 之间有 5 个字符，后者有 2 个字符。
     * 注意:
     * 1. Alice 和 Bob 是大小写敏感的，alice 或 bob 等并不计算在内。
     * 2. Alice 和 Bob 应为单独的单词，前后可以有标点符号和空格，但是不能 有字母。例如 Bobbi 並不算出现了 Bob。
     * 【输入格式】
     * 第一行包含一个整数 K。 第二行包含一行字符串，只包含大小写字母、标点符号和空格。长度不超 过 1000000。
     * 【输出格式】 输出一个整数，表示 Alice 和 Bob 同时出现的次数。
     * 【样例输入】 20 This is a story about Alice and Bob. Alice wants to send a private message to Bob.
     * 【样例输出】 2
     * 【评测用例规模与约定】 对于所有评测用例，1≤ K ≤1000000。
     */

    public static void main(String[] args) {
        Scanner scan= new Scanner(System.in);
        int k=scan.nextInt();
        int num=0;
        scan.nextLine();//吸收回车
        String str=scan.nextLine();
        String word[]=str.split("\\s+|\\.");//本题关键在于分离字符 注意.空格的组合存放为空字符串

        //Alice------>Bob
        for (int i=0;i<word.length;i++){
            if (word[i].equals("Alice")){
                //找接近的Bob
                for (int j=i+1;j<word.length;j++){
                    if (word[j].equals("Bob")){
                        int sum=1;//至少是有一个空格或者.分开 所以至少是1
                        for (int f=i+1;f<j;f++){
                            //计算距离
                            sum+=word[f].length()+1;
                        }
                        if (sum<=k)
                            num++;
                        else break;
                    }
                }
            }
        }
        //Bob------>Alice
        for (int i=0;i<word.length;i++){
            if (word[i].equals("Bob")){
                //找接近的Bob
                for (int j=i+1;j<word.length;j++){
                    if (word[j].equals("Alice")){
                        int sum=1;//至少是有一个空格或者.分开 所以至少是1
                        for (int f=i+1;f<j;f++){
                            //计算距离
                            sum+=word[f].length()+1;
                        }
                        if (sum<=k)
                            num++;
                        else break;
                    }
                }
            }
        }

        System.out.println(num);
    }
}
