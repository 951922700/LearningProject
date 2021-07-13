import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ConditionTest2 {
    //这里只对multiply方法进行测试
    private int input1;//输入参数
    private int input2;
    private int input3;
    private int expected;//期望值

    public ConditionTest2(int input1, int input2, int input3, int excpected) {
        this.input1 = input1;
        this.input2 = input2;
        this.input3 = input3;
        this.expected = excpected;
    }

    //假设result初值是2
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        Object[][] data=new Object[][]{
                {2,1,2,5},
                {2,1,0,4},
                {1,1,2,5},
                {2,2,0,5},
                {1,1,0,2},
                {2,2,-1,4},
                {1,2,2,6},
                {-1,1,2,3},
                {1,-1,-1,-1},
                {-1,1,-1,-1},
                {-1,-1,-2,-4},
                {-1,-1,-2,-4}
        };
        return Arrays.asList(data);
    }

    @Test
    public void test(){
        Conditon conditon=new Conditon();
        conditon.init(input1,input2,input3);
        conditon.cal();
        assertEquals(expected,conditon.getY());
    }
    /**
     * 条件组合覆盖
     * A>0&&B==1
     * A==2||X>1
     *  1.TTTT 2125
     *  2.TTTF 2104
     *  3.TTFT 1125
     *  4.TFTT 2205
     *  5.FTTT x无效 A为2不能使得A>0无效
     *  6.TTFF 1102
     *  7.TFTF 22-14
     *  8.FTTF x
     *  9.TFFT 1226
     * 10.FTFT -1123
     * 11.FFTT x
     * 12.TFFF 1-1-1-1
     * 13.FTFF -11-11
     * 14.FFTF x
     * 15.FFFT -1-12-2
     * 16.FFFF -1-1-2-4
     */
    @Test
    public void ConditonJudge(){
        Conditon conditon=new Conditon();
        conditon.init(input1,input2,input3);
        conditon.cal();
        System.out.println(conditon.getY());
    }

    @Test
    public void testCollections(){
      new HashMap<>();
    }
}
