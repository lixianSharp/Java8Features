package org.lixianyuan.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author lxy
 * @Date 2020/5/9
 * @Descript
 *  流(Stream)到底是什么呢？
 *      是数据渠道，用于操作数据源(集合、数组等)所生产的元素序列。"集合讲是数据，流将的是计算！"
 *     注意：
 *      1、Stream 自己不会存储元素
 *      2、Stream 不会改变源对象，相反，他们会返回一个持有结果的新Stream。
 *      3、Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
 *
 *     Stream的操作三个步骤：
 *      1、创建Stream
 *          一个数据源(如：集合、数组)，获取一个流。
 *      2、中间操作
 *          一个中间操作链，对数据源的数据进行处理。
 *      3、终止操作(终端操作)
 *          一个终止操作，执行中间操作链，并产生结果。
 **/
public class TestStreamAPI1 {

    //创建Stream
    @Test
    public void test1() {
        //1、可以通过Collection 系列集合提供的 stream()或 parallelStream()
        List<String> list = new ArrayList<String>();
        Stream<String> stream = list.stream();

        //2、通过Arrays 中的静态方法 stream() 获取数组流
        Employee[] emps = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(emps);

        //3、通过Stream 类中的静态方法 of()
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        //4、创建无限流
        //迭代
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream4.limit(10).forEach(System.out::println);

        //生成
        Stream.generate(()->Math.random()).limit(5)
                .forEach(System.out::println);
    }

}
