package string;

import java.lang.reflect.Field;


/**
 * new创建的字符串实例，是新对象，和字面量实例共享同一个char[]，所有字面量创建的对象共享同一个实例
 * <br>
 * 注：在jdk9之后， 不同module模块间是不允许使用反射来访问非public的字段/方法/构造函数（field/method/constructor），
 * * 除非被访问的目标模块为反射设置open即允许自身被其他模块进行non-public反射访问。
 * *
 */
public class DiveInString {
    public static void main(String[] args) throws Exception {
        String s1 = new String("abc");
        String s2 = new String("abc");
        Field value = s1.getClass().getDeclaredField("value");
        value.setAccessible(true);
        char[] c1 = {'a', 'b', 'f'};
        value.set(s1, c1);
        // 实际上本身指向的是同一个数组，是在编译期依赖的字面量"abc"在StringTable中共享的char[]
        // 反射将s1的value指向了另一个数组
        System.out.println(s1); // 被修改
        System.out.println(s2); // 未修改

        String s3 = "abc";
        String s4 = "abc";
        Field field2 = s3.getClass().getDeclaredField("value");
        char[] c2 = {'a', 'b', 'm'};
        field2.setAccessible(true);
        field2.set(s3, c2);
        // 因为s3和s4实际上共享StringTable的value指向的实例，所以修改一个会导致所有都被修改
        // 甚至于后续再通过字面量创建的实例都会被污染
        System.out.println(s3); // 被修改
        System.out.println(s4); // 被修改
        String s5 = "abc";
        System.out.println(s5);
    }
}
