import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * 进行参数化测试步骤
 * 1.对测试类添加注解 @RunWith(Parameterized.class)
 * 2.将需要使用变化范围参数值测试的参数定义为私有变量
 * 3.使用上一步骤声明的私有变量作为入参，创建构造函数
 * 4.创建一个使用@Parameters注解的公共静态方法，它将需要测试的各种变量值通过集合的形式返回
 * 5.使用定义的私有变量定义测试方法
 */
@RunWith(Parameterized.class)
public class ParameterTest {
    //这里只对multiply方法进行测试
    private int input;//输入参数
    private int expected;//期望值

    public ParameterTest(int input, int excpected) {
        this.input = input;
        this.expected = excpected;
    }

    //假设result初值是2
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        Object[][] data=new Object[][]{
                {2,4},
                {3,6},
                {5,10}
        };
        return Arrays.asList(data);
    }

    @Test
    public void testMultiply(){
        Calculator calculator=new Calculator();
        calculator.add(2);
        calculator.multiply(input);
        assertEquals(expected,calculator.getResult());
    }
}
