package ArrayAndString.String;

public class compareTest {
    // "static void main" must be defined in a public class.

        public static void main(String[] args) {
            // initialize
            String s1 = "Hello World";
            System.out.println("s1 is \"" + s1 + "\"");
            String s2 = s1;
            System.out.println("s2 is another reference to s1.");
            String s3 = new String(s1);
            System.out.println("s3 is a copy of s1.");
            // compare using '=='
            System.out.println("Compared by '==':");
            // true since string is immutable and s1 is binded to "Hello World"
            System.out.println("s1 and \"Hello World\": " + (s1 == "Hello World"));
            // true since s1 and s2 is the reference of the same object
            System.out.println("s1 and s2: " + (s1 == s2));
            // false since s3 is refered to another new object
            System.out.println("s1 and s3: " + (s1 == s3));
            // compare using 'equals'
            System.out.println("Compared by 'equals':");
            System.out.println("s1 and \"Hello World\": " + s1.equals("Hello World"));
            System.out.println("s1 and s2: " + s1.equals(s2));
            System.out.println("s1 and s3: " + s1.equals(s3));
            // compare using 'compareTo'
            System.out.println("Compared by 'compareTo':");
            System.out.println("s1 and \"Hello World\": " + (s1.compareTo("Hello World") == 0));
            System.out.println("s1 and s2: " + (s1.compareTo(s2) == 0));
            System.out.println("s1 and s3: " + (s1.compareTo(s3) == 0));


            //java字符串不可变
           /* ArrayAndString.String s4 = "Hello World";
            s4[5] = ',';
            System.out.println(s4);*/

            /**
             * 在 字符串不可变 的语言中，进行字符串的连接操作则会带来一些问题。
             * 显然，不可变字符串无法被修改。哪怕你只是想修改其中的一个字符，也必须创建一个新的字符串。
             * 我们以 Java 为例，来看一个在 for 循环中重复进行字符串连接的例子：
             * public static void main(ArrayAndString.String[] args) {
             *         ArrayAndString.String s = "";
             *         int n = 10000;
             *         for (int i = 0; i < n; i++) {
             *             s += "hello";
             *         }
             *     }
             *     我们发现在 C++ 中，进行字符串连接并没有明显的性能影响。
             *
             * 然而，对于 Java来说，由于字符串是不可变的，因此在连接时首先为新字符串分配足够的空间，复制旧字符串中的内容并附加到新字符串。
             *如果你确实希望你的字符串是可变的，则可以使用 toCharArray 将其转换为字符数组。
             * 如果你经常必须连接字符串，最好使用一些其他的数据结构，如 StringBuilder
             *
             */
        }

}
