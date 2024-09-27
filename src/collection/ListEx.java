package collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 断点调试类
 */
public class ListEx {
    public static void main(String[] args) {
//        arrayList();
        hashMap();
        ReentrantLock lock = new ReentrantLock();
    }

    private static void hashMap() {
        /*
          resize时，如果树小于6，会从红黑树退回链表
         */
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 10);
    }

    private static void arrayList() {
        /*
        ArrayList首次创建如果没有指定初始化大小，默认是一个长度为0 的空数组，第一次插入值才变为10或插入元素个数+1
         */
        ArrayList<Integer> list = new ArrayList<>();
//        list.add(1);
        list.addAll(List.of(1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6));
    }
}
