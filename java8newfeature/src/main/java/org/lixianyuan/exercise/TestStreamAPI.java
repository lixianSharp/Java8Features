package org.lixianyuan.exercise;

import org.junit.Test;
import org.lixianyuan.lambda.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author lxy
 * @Date 2020/5/27
 * @Descript
 **/
public class TestStreamAPI {

    /**
     * 1、给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
     *  给定【1，2，3，4，5】，应该返回【1，4，8，16，25】
     */
    @Test
    public void test1(){
        Integer[] nums = new Integer[]{1,2,3,4,5};
        Arrays.stream(nums)
                .map((num)->num*num)
                .forEach(System.out::println);
    }


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

    /**
     * 2、怎样用map和reduce方法数一数流中有多少个Employee呢？
     */
    @Test
    public void test2(){
        Optional<Integer> op = employees.stream()
                .map((emp) -> 1)
                .reduce((x, y) -> x + y);
        Integer count = op.get();
        System.out.println(count);
    }
}
