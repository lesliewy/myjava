package api.util.stream;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import wy.Employee;

/**
 * Created by leslie on 2017/7/23.
 */
public class Stream1 {

    /**
     * forEach是结束操作.
     */
    @Test
    public void test1() {
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        stream.forEach(str -> System.out.println(str));
    }

    /**
     * filter是中间操作，所以如果只调用filter不会有实际计算，也不会输出.
     */
    @Test
    public void test2() {
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        stream.filter(str -> str.length() == 3).forEach(str -> System.out.println(str));
    }

    @Test
    public void test3() {
        Stream<String> stream = Stream.of("I", "love", "you", "too", "too");
        stream.distinct().forEach(str -> System.out.println(str));
    }

    @Test
    public void test4() {
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        stream.sorted((str1, str2) -> str1.length() - str2.length()).forEach(str -> System.out.println(str));
    }

    @Test
    public void test5() {
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        stream.map((str -> str.toUpperCase())).forEach(str -> System.out.println(str));
    }

    @Test
    public void test6() {
        Stream<List> stream = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4, 5));
        stream.flatMap(list -> list.stream()).forEach(i -> System.out.println(i));
    }

    /**
     * Optional<T> reduce(BinaryOperator<T> accumulator);
     */
    @Test
    public void test7() {
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Optional<String> longest = stream.reduce((s1, s2) -> s1.length() >= s2.length() ? s1 : s2);
        // Optional<String> longest = stream.max((s1, s2) -> s1.length() - s2.length());
        System.out.println(longest.get());
    }

    /**
     * <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);
     */
    @Test
    public void test8() {
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Integer lengthSum = stream.reduce(0, (sum, str) -> sum + str.length(), (a, b) -> a + b);
        // int lengthSum = stream.mapToInt(str -> str.length()).sum();
        System.out.println(lengthSum);
    }

    /**
     * <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner);
     * 1. 目标容器是什么？ ArrayList, HashSet, TreeMap
     * 2. 新元素如何添加？ List.add()  Map.put()
     * 3. 并行时，多个部分结构如何合并成一个?
     *
     *
     * 方法引用:
     *    引用静态方法:  Integer :: sum
     *    引用某个对象的方法:   list :: add
     *    引用某个类的方法:     String :: length
     *    引用构造方法:         HashMap :: new
     */
    @Test
    public void test9(){
        Stream<String> stream = Stream.of("I", "love", "you", "too");
//        List<String> list = stream.collect(Collectors.toList());
//        Set<String> set = stream.collect(Collectors.toSet());
//        Map<String, Integer> map = stream.collect(Collectors.toMap(Function.identity(), String::length));

        List<String> list1 = stream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        // 等价于上面, (list, str) 顺序不能颠倒.
//        List<String> list1 = stream.collect( () -> new ArrayList<>(), (list, str) -> list.add(str), (list, strs) -> list.addAll(strs));
//        System.out.println(list);
//        System.out.println(set);
//        System.out.println(map);
        System.out.println(list1);
    }

    /**
     * 指定规约容器的类型
     */
    @Test
    public void test10(){
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        ArrayList<String> arrayList = stream.collect(Collectors.toCollection(ArrayList::new));
//        HashSet<String> hashSet = stream.collect(Collectors.toCollection(HashSet::new));
    }

    @Test
    public void test11(){
        Employee employee = new Employee();
        System.out.println(employee);

        System.out.println(string2MD5("13393780602"));
    }

    private String string2MD5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }
}
