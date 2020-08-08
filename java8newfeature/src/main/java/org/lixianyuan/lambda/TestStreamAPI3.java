package org.lixianyuan.lambda;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lxy
 * @Date 2020/5/13
 * @Descript 终止操作
 **/
public class TestStreamAPI3 {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99, Employee.Status.BUSY),
            new Employee("李四", 38, 5555.99,Employee.Status.BUSY),
            new Employee("王五", 50, 6666.66,Employee.Status.BUSY),
            new Employee("赵六", 70, 3333.33,Employee.Status.BUSY),
            new Employee("田七", 8, 7777.77,Employee.Status.FREE),
            new Employee("田七", 8, 7777.77,Employee.Status.FREE),
            new Employee("田七", 8, 7777.77,Employee.Status.VOCATION),
            new Employee("田七", 8, 7777.77,Employee.Status.VOCATION)
    );


    /*
     查找与匹配
        allMatch --检查是否匹配所有元素
        anyMatch -- 检查是否至少匹配一个元素
        noneMatch -- 检查是否没有匹配所有元素
        findFirst --返回第一个元素
        findAny -- 返回当前流中任意元素
        count -- 返回六中元素的总个数
        max -- 返回流中最大值
        min -- 返回流中最小值
     */
    @Test
    public void test1(){
        boolean b = employees.stream()
                .allMatch((emps) -> emps.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);//false

        boolean b1 = employees.stream()
                .anyMatch((emps) -> emps.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);//true

        boolean b2 = employees.stream()
                .noneMatch((emps) -> emps.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);//false 因为只匹配了部分，没有匹配所有，所以是false

        Optional<Employee> op = employees.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(op.get());//Employee{name='赵六', age=16, salary=3333.33, status=BUSY}

        Optional<Employee> op2 = employees.parallelStream()
                .filter((e)->e.getStatus().equals(Employee.Status.BUSY))
                .findAny();
        System.out.println(op2.get());
    }


    @Test
    public void test2(){
        long count = employees.stream()
                .count();
        System.out.println(count);//8

        Optional<Employee> op1 = employees.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(op1.get());//Employee{name='张三', age=18, salary=9999.99, status=BUSY}

        Optional<Double> op2 = employees.stream()
                .map((emps) -> {
                    return emps.getSalary();
                })
                .min(Double::compare);
        System.out.println(op2);//Optional[3333.33]
    }

    /*
    规约
    reduce(T identity, BinaryOperator) / reduce(BinaryOperator) --可以将流中元素反复结合起来，得到一个值
     */
    @Test
    public void test3() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);//identity表示最后还要加上 identity的值，当前是为0
        System.out.println(sum);

        System.out.println("-----------------------");
        Optional<Double> op = employees.stream()
                .map((emp) -> emp.getSalary())
                .reduce((x, y) -> x + y);
              //  .reduce(Double::sum); //这句和上一句等价
        System.out.println(op.get());

    }

    /*
    收集
    collect --- 将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法。
    Collector 接口中方法的实现决定了如何对流执行收集操作(如收集到List、Set、Map)。但是 Collectors 实用类提供
        了很多静态方法，可以方便的创建创建收集器实例，具体方法与示例如下：
     */
    @Test
    public void test4() {
        List<String> list = employees.stream()
                .map((emp) -> emp.getName())
                .collect(Collectors.toList());
        list.forEach(System.out::println);

        System.out.println("--------------------");

        Set<String> set = employees.stream()
                .map((emp) -> emp.getName())
                .collect(Collectors.toSet());
        set.forEach(System.out::println);

        System.out.println("-------------------------");

        HashSet<String> hs = employees.stream()
                .map((emp) -> emp.getName())
                .collect(Collectors.toCollection(HashSet::new));
//                .collect(Collectors.toCollection(()->new HashSet<>()); //这句等价于上面的一句
        hs.forEach((str)->System.out.println(str));
//        hs.forEach(System.out::println);//这句和上面一句等价

    }

    @Test
    public void test5() {
        //总数
        Long count = employees.stream()
                .collect(Collectors.counting());
        System.out.println(count); //8

        System.out.println("--------------------");

        //平均值
        Double avg = employees.stream()
                .collect(Collectors.averagingDouble((emp) -> emp.getSalary()));
        System.out.println(avg);

        //所有员工薪水总和
        Double sum = employees.stream()
                .collect(Collectors.summingDouble((emp) -> emp.getSalary()));
        System.out.println(sum);

        //最大值 薪水的最大值
        Optional<Employee> max = employees.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(max.get());

        //最小值 薪水的最小值
        Optional<Employee> min = employees.stream()
                .collect(Collectors.minBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(min.get());

    }

    //分组
    @Test
    public void test6() {
        Map<Employee.Status, List<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy((emp) -> emp.getStatus()));// 按状态分组
        System.out.println(map);
        //{BUSY=[Employee{name='张三', age=18, salary=9999.99, status=BUSY}, Employee{name='李四', age=38, salary=5555.99, status=BUSY}, Employee{name='王五', age=50, salary=6666.66, status=BUSY}, Employee{name='赵六', age=16, salary=3333.33, status=BUSY}], FREE=[Employee{name='田七', age=8, salary=7777.77, status=FREE}, Employee{name='田七', age=8, salary=7777.77, status=FREE}], VOCATION=[Employee{name='田七', age=8, salary=7777.77, status=VOCATION}, Employee{name='田七', age=8, salary=7777.77, status=VOCATION}]}
    }

    //多级分组
    @Test
    public void test7() {
        Map<Employee.Status, Map<String, List<Employee>>> map = employees.stream()
                .collect(Collectors.groupingBy((emp) -> emp.getStatus(), Collectors.groupingBy((e) -> {
                    if (e.getAge() <= 35) {
                        return "青年";
                    } else if (e.getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(map);
        //{VOCATION={青年=[Employee{name='田七', age=8, salary=7777.77, status=VOCATION}, Employee{name='田七', age=8, salary=7777.77, status=VOCATION}]}, BUSY={青年=[Employee{name='张三', age=18, salary=9999.99, status=BUSY}], 老年=[Employee{name='赵六', age=70, salary=3333.33, status=BUSY}], 中年=[Employee{name='李四', age=38, salary=5555.99, status=BUSY}, Employee{name='王五', age=50, salary=6666.66, status=BUSY}]}, FREE={青年=[Employee{name='田七', age=8, salary=7777.77, status=FREE}, Employee{name='田七', age=8, salary=7777.77, status=FREE}]}}
    }

    //分区
    @Test
    public void test8(){
        Map<Boolean, List<Employee>> map = employees.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 8000));
        System.out.println(map);
        //{false=[Employee{name='李四', age=38, salary=5555.99, status=BUSY}, Employee{name='王五', age=50, salary=6666.66, status=BUSY}, Employee{name='赵六', age=70, salary=3333.33, status=BUSY}, Employee{name='田七', age=8, salary=7777.77, status=FREE}, Employee{name='田七', age=8, salary=7777.77, status=FREE}, Employee{name='田七', age=8, salary=7777.77, status=VOCATION}, Employee{name='田七', age=8, salary=7777.77, status=VOCATION}], true=[Employee{name='张三', age=18, salary=9999.99, status=BUSY}]}
    }


    //统计
    @Test
    public void test9(){
        DoubleSummaryStatistics dss = employees.stream()
                .collect(Collectors.summarizingDouble((e) -> e.getSalary()));
        System.out.println(dss.getSum());//56667.05
        System.out.println(dss.getAverage());//7083.38125
        System.out.println(dss.getMax());//9999.99
    }

    @Test
    public void test10(){
        String str = employees.stream()
                .map((e) -> e.getName())
                .collect(Collectors.joining(",", "===", "-----"));
        System.out.println(str);//===张三,李四,王五,赵六,田七,田七,田七,田七-----

    }
}
