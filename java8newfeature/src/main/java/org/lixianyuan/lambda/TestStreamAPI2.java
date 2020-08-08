package org.lixianyuan.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author lxy
 * @Date 2020/5/11
 * @Descript 一、Stream 的三个操作步骤：
 * 1、创建 Stream
 * 2、 中间操作
 * 3、终止操作(终端操作)
 **/
public class TestStreamAPI2 {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 5555.99),
            new Employee("王五", 50, 6666.66),
            new Employee("赵六", 16, 3333.33),
            new Employee("田七", 8, 7777.77)
    );

    //中间操作
    /*
    筛选与切片
    filter--接收 Lambda,从流中排除某些元素
    limit--截断流，使元素不超过给定数量。
    skip-- 跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。若流中元素不足n个，则返回一个空流。与limit(n)互补。
    distinct -- 筛选，通过流所生产元素的 hashCode() 和 equals() 去除重复元素。
    */
    @Test
    public void test4() {
        employees.stream()
                .filter((emp) -> emp.getSalary() > 5000)
                .skip(2)  //跳过两个元素，返回一个扔掉了前两个元素的流。
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test4_extend(){
        List<String> list = new ArrayList<String>();
        Stream<Employee> stm = employees.stream()
                .filter((emp) -> emp.getSalary() > 5000)
                .skip(2)  //跳过两个元素，返回一个扔掉了前两个元素的流。
                .distinct();
        stm.forEach((employee)->list.add(employee.getName()));//将遍历出来的所有名字放在list集合中。
        System.out.println(list);
    }
    @Test
    public void test3(){
        employees.stream()
                .filter((emp)->{
                    System.out.println("短路"); // && ||
                    return emp.getSalary()>5000;
                })
                .limit(2)
                .forEach(System.out::println);
    }

    //内部迭代：迭代操作由Stream API完成。
    @Test
    public void test1(){
        /*
        多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何的处理！而在终止操作时一次性全部处理，称为"惰性求值"
        */
        //中间操作：不会执行任何操作
        Stream<Employee> employeeStream = employees.stream()
                .filter((emp) -> {
                    System.out.println("Stream API 的中间操作");
                    return emp.getAge() > 35;
                });

        //终止操作：一次性执行全部内容，即惰性求值
        employeeStream.forEach(System.out::println);//如果把这行注释掉，什么都不会输出，说明只有被用到的时候才会执行，类似懒加载
    }



    //外部迭代
    @Test
    public void test2(){
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            System.out.println(employee);
        }
    }


    /*映射
    map -- 接收 Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
    flatMap -- 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
    */
    @Test
    public void test5() {
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");

        list.stream()
                .map((str)->str.toUpperCase())
                .forEach(System.out::println);
        /**
         * AAA
         * BBB
         * CCC
         * DDD
         * EEE
         */
        System.out.println("---------------------------");
        employees.stream()
                .map(Employee::getName)
//                .map((emp)->emp.getName())
                .forEach(System.out::println);
        /**
         * 张三
         * 李四
         * 王五
         * 赵六
         * 田七
         */

        System.out.println("--------------------------");
        Stream<Stream<Character>> stream = list.stream()
                    .map(str->filterCharacter(str));
//                .map(TestStreamAPI2::filterCharacter);
        stream.forEach((sm)->{
            sm.forEach(System.out::println);
        });
        /**
         * a
         * a
         * a
         * b
         * b
         * b
         * c
         * c
         * c
         * d
         * d
         * d
         * e
         * e
         * e
         */

        System.out.println("------------------");

        Stream<Character> characterStream = list.stream()
//                .flatMap((str)->filterCharacter(str));
                .flatMap(TestStreamAPI2::filterCharacter);
        characterStream.forEach((cs)->{
            System.out.println(cs);
        });
        /**
         * a
         * a
         * a
         * b
         * b
         * b
         * c
         * c
         * c
         * d
         * d
         * d
         * e
         * e
         * e
         */
    }

    @Test
    public void test6(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");

        List list2 = new ArrayList();

        list2.add(11);
        list2.add(12);
        list2.addAll(list);

        System.out.println(list2);// [11, 12, aaa, bbb, ccc, ddd, eee]
    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<Character>();
        for(Character ch:str.toCharArray()){
            list.add(ch);
        }

        return list.stream();
    }


    /**
     * 排序
     * sorted() --自然排序
     * sorted(Comparator com) -- 定制排序
     */
    @Test
    public void test7() {
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");
        list.stream()
                .sorted()
                .forEach(System.out::println);
        /**
         * aaa
         * bbb
         * ccc
         * ddd
         * eee
         */
        System.out.println("-----------------");
        List<Employee> employees2 = Arrays.asList(
                new Employee("张三", 18, 9999.99),
                new Employee("李四", 38, 5555.99),
                new Employee("李四", 38, 6667.66),
                new Employee("李四", 38, 6669.66),
                new Employee("李四", 38, 6666.66),
                new Employee("王五", 50, 6666.66),
                new Employee("赵六", 16, 3333.33),
                new Employee("田七", 8, 7777.77)
        );
        employees2.stream()
                .sorted((e1,e2)->{
                    //年龄相同比姓名，年龄不同比薪水
                   if(e1.getAge()==e2.getAge()){
                       return e1.getName().compareTo(e2.getName());
                   }else{
                       return Double.compare(e1.getSalary(), e2.getSalary());
                   }
                })
                .forEach(System.out::println);
        /**
         * Employee{name='赵六', age=16, salary=3333.33}
         * Employee{name='李四', age=38, salary=5555.99}
         * Employee{name='王五', age=50, salary=6666.66}
         * Employee{name='李四', age=38, salary=6667.66}
         * Employee{name='李四', age=38, salary=6669.66}
         * Employee{name='李四', age=38, salary=6666.66}
         * Employee{name='田七', age=8, salary=7777.77}
         * Employee{name='张三', age=18, salary=9999.99}
         */


    }

}
