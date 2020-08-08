package org.lixianyuan.lambda;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author lxy
 * @Date 2020/5/9
 * @Descript
 *  一、方法引用：若Lambda 体中的内容有方法已经实现了，我们可以使用“方法引用”
 *              (可以理解为方法引用是Lambda表达式的另一种表现形式)
 *   主要有三种语法格式：
 *      对象::实例方法名
 *
 *      类::静态方法名
 *
 *      类::实例方法名
 *    注意：
 *      1、Lambda体重调用方法的参数列表与返回值类型，要与函数式接口中的抽象方法的函数列表和返回值类型一致。
 *      2、若Lambda参数列表中的第一参数是 实例方法的调用者，二第二个参数是实例方法的参数时，可以使用ClassName ::
 *
 *  二、构造器引用
 *      格式：
 *          ClassName::new
 *        注意：需要调用的构造器的参数列表要与函数式接口中的重修昂方法的参数列表保持一致！！
 *
 *  三、数组引用
 *      Type[]::new
 **/
public class TestMethodRef {

    //数组引用：
    @Test
    public void test7() {
        Function<Integer,String[]> fun = (x) -> new String[x];
        String[] str = fun.apply(10);
        System.out.println(str.length);

        Function<Integer,String[]> fun2 = String[]::new;
        String[] str2 = fun2.apply(10);
        System.out.println(20);
    }

    //构造器引用
    @Test
    public void test5(){
        Supplier<Employee> supplier = () -> new Employee();

        //构造器引用方式
        Supplier<Employee> supplier2 = Employee::new;
        Employee employee = supplier2.get();
        System.out.print(employee);//Employee{name='null', age=0, salary=0.0}
    }

    @Test
    public void test6() {
        Function<Integer,Employee> fun = (x) ->new Employee(x);
        Employee emp1 = fun.apply(99);
        System.out.println(emp1);//Employee{name='null', age=99, salary=0.0}

        System.out.println("----------------------------");

        Function<Integer,Employee> fun2 = Employee::new;
        Employee emp2 = fun2.apply(23);
        System.out.println(emp2);//Employee{name='null', age=23, salary=0.0}

    }
    //类::实例方法名
    @Test
    public void test4(){
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);

        BiPredicate<String,String> bp2 = String::equals;

    }


    //类::静态方法名
    @Test
    public void test3(){
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

        Comparator<Integer> com1 = Integer::compareTo;
    }

    //对象::实例方法名
    @Test
    public void test1(){
        PrintStream ps1 = System.out;

        Consumer<String> com = (x) -> ps1.print(x);

        PrintStream ps = System.out;
        Consumer<String> con1 = ps::println;
        con1.accept("哈哈哈哈");

        Consumer<String> con2 = System.out::println;
        con2.accept("abcdef");

        /**
         * 哈哈哈哈
         * abcdef
         */
    }

    @Test
    public void test2() {
        Employee emp = new Employee();
        emp.setAge(18);
        emp.setName("张三");

        Supplier<String> sup = ()->emp.getName();
        String str = sup.get();
        System.out.println(str);//张三

        Supplier<Integer> sup2 = emp::getAge;
        Integer pnum = sup2.get();
        System.out.println(pnum);//18
    }
}
