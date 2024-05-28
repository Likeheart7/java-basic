package bridginginheritance;

public class Parent<T> {
    protected void testBridging(T obj){
        System.out.println(obj);
    }
}
