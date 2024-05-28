package action;


import java.text.DecimalFormat;

public class NumberAction {
    public static void main(String[] args){
        formatNumber();
    }

    private static void formatNumber() {
        double num = 30.14;
        System.out.println(new DecimalFormat("000.000").format(num));    // 占位符0会补齐
        System.out.println(new DecimalFormat("###.000").format(num));    // 占位符#不会补齐
        System.out.println(new DecimalFormat("0.00E0").format(num));    // 占位符#不会补齐
    }
}
