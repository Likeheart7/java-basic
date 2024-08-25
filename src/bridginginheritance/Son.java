package bridginginheritance;

import java.util.Arrays;

public class Son extends Parent<Pojo> {
    @Override
    protected void testBridging(Pojo obj) {
        super.testBridging(obj);
    }

    public static void main(String[] args) throws NoSuchMethodException {
        /*
        使用javap反编译的Son类
        public class bridginginheritance.Son extends bridginginheritance.Parent<bridginginheritance.Pojo> {
          public bridginginheritance.Son();
          protected void testBridging(bridginginheritance.Pojo);
          public static void main(java.lang.String[]) throws java.lang.NoSuchMethodException;
          protected void testBridging(java.lang.Object);
        }
        javap -c 反编译的结果
          protected void testBridging(java.lang.Object);
          protected void testBridging(java.lang.Object);
            Code:
               0: aload_0
               1: aload_1
            Code:
               0: aload_0
               1: aload_1
               2: checkcast     #35                 // class bridginginheritance/Pojo
               5: invokevirtual #37                 // Method testBridging:(Lbridginginheritance/Pojo;)V
               8: return
        }

        可以看到实际上是在testBridging(java.lang.Object)中通过invokevirtual调用了Method testBridging:(Lbridginginheritance/Pojo;)
         */
//        Son在重写testBridging方法的时候，由于父类泛型的类型擦除，实际重写的是参数为Object的方法，在该方法内部调用参数为Pojo的方法
        Arrays.stream(Son.class.getDeclaredMethods()).forEach(t -> System.out.println(t.getName() + "  " + Arrays.toString(t.getGenericParameterTypes())));
    }
}
