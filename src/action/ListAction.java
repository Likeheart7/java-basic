package action;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListAction {
    public static void main(String[] args) {
//        listConvert();
        listRemove();
    }

    private static void listConvert() {
        int[] arr = {1, 2, 3, 4, 5};
        String[] strs = {"a", "b", "C"};
//        适用于对象型数据
        System.out.println(Arrays.asList(strs));
        ArrayList<String> strList = new ArrayList<>(strs.length);
        Collections.addAll(strList, strs);
        System.out.println(strList);
        // 处理整型
        System.out.println(IntStream.of(arr).boxed().collect(Collectors.toList()));
        List<String> list = new ArrayList<>(Arrays.asList(strs));   // asList返回的集合不可修改
        list.add("abcda");
        System.out.println(list);

        // 列表转数组
        String[] array = strList.toArray(new String[0]);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 列表遍历删除
     */
    public static void listRemove() {
        ArrayList<String> list = new ArrayList<>(List.of("1", "2", "3", "4", "4", "5"));
        // java.util.ConcurrentModificationException
//        list.forEach(item -> {
//            if (item.equals("4")) {
//                list.remove(item);
//            }
//        });
        // 遍历删除
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals("4")) {
                iterator.remove();
            }
        }
//        list.removeIf(item -> item.equals("4"));    // 删除所有的某个值，可以用来替换遍历删除
        System.out.println(list);
    }
}
