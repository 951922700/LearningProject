import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ConditionTest1 {
    //这里只对multiply方法进行测试
    private int input1;//输入参数
    private int input2;
    private int input3;
    private int expected;//期望值

    public ConditionTest1(int input1, int input2, int input3, int excpected) {
        this.input1 = input1;
        this.input2 = input2;
        this.input3 = input3;
        this.expected = excpected;
    }

    //假设result初值是2
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        Object[][] data=new Object[][]{
                {2,1,3,5},
                {3,1,0,4},
                {-1,1,3,4},
                {-1,1,0,0}
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
     *判断覆盖
     * 使得每个路径走一遍
     * TT A=2 B=1 X=3  Y=5
     * TF A=3 B=1 X=0  Y=4
     * FT A=-1 B=1 X=3 Y=4
     * FF A=-1 B=1 X=0 Y=0
     */
    /*@Test
    public void ConditonJudge(){
        Conditon conditon=new Conditon();
        conditon.init(2,1,3);
        conditon.cal();
        System.out.println(conditon.getY());
        conditon.init(3,1,0);
        conditon.cal();
        System.out.println(conditon.getY());
        conditon.init(-1,1,3);
        conditon.cal();
        System.out.println(conditon.getY());
        conditon.init(-1,1,0);
        conditon.cal();
        System.out.println(conditon.getY());
    }*/
}
