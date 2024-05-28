package action;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringAction {
    public static void main(String[] args) {
        printfString();
        System.out.println("------------------------------");
        string2Collection();
        System.out.println("--------------- 驼峰转下划线 --------------------");
        toUnderlineCase();
        System.out.println("--------------- 下划线转驼峰 --------------------");
        toCamelCase();
    }

    /*
     * 字符串格式化输出
     */
    public static void printfString(){
        String str = "like heart";
        String res = String.format("I am %s", str);
        System.out.println(res);
        // 一个字符串替换多个占位
        String fmt = MessageFormat.format("I''m {0}, {0}", str);
        System.out.println(fmt);
    }

    /**
     * 字符串、集合转换
     */
    public static void string2Collection(){
        // 字符串转集合
        String str = "29959979";
        String[] res = str.split("");
        System.out.println(Arrays.toString(res));
        System.out.println(new ArrayList<>(Arrays.asList(res)));
        // 转成int
        List<Integer> integerList = Stream.of(res).map(Integer::valueOf).collect(Collectors.toList());
        System.out.println(integerList);

        // 集合转字符串
        String join = integerList.stream().map(String::valueOf).collect(Collectors.joining("."));
        System.out.println(join);
    }

    /**
     * 驼峰转下划线
     */
    public static void toUnderlineCase() {
        String str = "helloWorld";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cur = str.charAt(i);
            if (Character.isUpperCase(cur)) {
                sb.append("_");
                sb.append(Character.toLowerCase(cur));
            } else {
                sb.append(cur);
            }
        }
        System.out.println(sb);

    }

    /**
     * 下划线转驼峰
     */
    public static void toCamelCase(){
        String str = "hello_mrs_world";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cur = str.charAt(i);
            if (cur =='_') {
                i++;
                sb.append(Character.toUpperCase(str.charAt(i)));
            } else {
                sb.append(cur);
            }
        }
        System.out.println(sb);
    }
}
