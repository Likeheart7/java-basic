package string;

import java.lang.reflect.Field;


/**
 * 通过反射修改String使其与另一个String可以equals，并且此时两个String指向不同对象
 * 此时和被修改的String共享同一个字符串的所有String实例都会被修改
 * 并且后续所有赋值为affective的变量都受到影响。
 * 所以本例仅在JDK 8环境可用
 * <br>
 * 注：在jdk9之后， 不同module模块间是不允许使用反射来访问非public的字段/方法/构造函数（field/method/constructor），
 *  * 除非被访问的目标模块为反射设置open即允许自身被其他模块进行non-public反射访问。
 *  *
 */
public class DiveInString {
    public static void main(String[] args) throws Exception {
        String first = "effective";
        String second = "affective";
        String third = "affective";
        System.out.println("first == second -> " + (first == second));
        System.out.println("first equals second -> " + (first == second));
        Field field = second.getClass().getDeclaredField("value");
        field.setAccessible(true);
        char[] arrayOfSecondValue = (char[]) field.get(second);
        arrayOfSecondValue[0] = 'e';
        System.out.println("now second is " + second);
        System.out.println("now first == second -> " + (first == second));
        System.out.println("now first equals second -> " + first.equals(second));

        System.out.println("and now the third string is " + third); // be modified too
        // 并且后续所有赋值为affective的变量都受到影响
        String fourth = "affective";
        System.out.println("fourth = " + fourth);   // fourth = effective
        String fifth = new String("affective");

        System.out.println(fifth);  // effective
    }
}
