package bridginginheritance;

import java.util.Arrays;

public class Son extends Parent<Pojo> {
    @Override
    protected void testBridging(Pojo obj) {
        super.testBridging(obj);
    }

    public static void main(String[] args) throws NoSuchMethodException {
        /*
         * testBridging  [class bridginginheritance.Pojo]
         * testBridging  [class java.lang.Object]   重写的方法
         * main  [class [Ljava.lang.String;]
         * lambda$main$0  [class java.lang.reflect.Method]
         */
//        Son在重写testBridging方法的时候，由于父类泛型的类型擦除，实际重写的是参数为Object的方法，在该方法内部调用参数为Pojo的方法
        Arrays.stream(Son.class.getDeclaredMethods()).forEach(t -> System.out.println(t.getName() + "  " +  Arrays.toString(t.getGenericParameterTypes())));
    }
}
