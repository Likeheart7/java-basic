import java.util.concurrent.*;

public class GlobalDemo {
    public static void main(String[] args) {
        System.out.println(convert("AB", 2));
    }
    public static String convert(String s, int numRows) {
        if (numRows <= 1) return s;
        final StringBuilder[] strings = new StringBuilder[numRows];
        int i = 0; int flag = -1;
        for (char c : s.toCharArray()) {
            if (strings[i] == null) strings[i] = new StringBuilder();
            strings[i].append(c);
            if (i == 0 || i == numRows - 1) flag = -flag;
            i += flag;
        }
        final StringBuilder res = new StringBuilder();
        for (StringBuilder str : strings){
            res.append(str);
        }
        return res.toString();
    }
}
