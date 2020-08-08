package org.lixianyuan.lambda;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author lxy
 * @Date 2020/5/7
 * @Descript
 * 一、Lambda表达式的基础语法：Java8中引入了一个新的操作符"->" 该操作符成为箭头操作符或Lambda操作符，
 *                  箭头操作符将Lambda 表达式拆分为两部分
 *               左侧： Lambda表达式的参数列表
 *               右侧：Lambda表达式中所需执行的功能，即Lambda体
 *
 *         语法格式一：无参数、无返回值
 *                  () -> System.out.println("Hello Lambda!");
 *
 *         语法格式二：有参数，并且无返回值
 *                  (x) -> System.out.println(x);
 *
 *         语法格式三：若只有一个参数，小括号可以省略不写。
 *
 *         语法格式四：有两个以上的参数，有返回值，并且Lambda体中有多条语句
 *                      Comparator<Integer> com = (x,y)->{
 *                              System.out.println("函数式接口");
 *                              return Integer.compare(x, y);
 *                      };
 *
 *         语法格式五：若Lambda 体中只有一条语句，return和大括号都可以省略不写
 *                      Comparator<Integer> com = (x,y)->Integer.compare(x,y);
 *
 *         语法格式六： Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即"类型推断"。
 *                          (Integer x,Integer y) -> Integer.compare(x,y);
 *
 * 二、Lambda表达式需要“函数式接口” 的支持
 *  函数式接口：接口中只有一个抽象方法的接口，成为函数式接口。可以使用@FunctionInterface 修饰
 *              可以检查是否是函数式接口。
 *
 **/
public class TestLambda2 {

    @Test
    public void test1(){
        final int num = 0; //jdk1.7之前必须是final
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World"+num);
            }
        };

        r.run(); // Hello World
        System.out.println("--------------------------------");

        Runnable r1 = () -> System.out.println("Hello Lambda!");
        r1.run(); // Hello Lambda!
    }

    @Test
    public void test2(){
        Consumer<String> com = (x) -> System.out.println(x);
        com.accept("我大Java威武！！");
    }

    @Test
    public void test3(){
        Comparator<Integer> com = (x,y)->{
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
    }

    @Test
    public void test4(){
        Comparator<Integer> com = (x,y)->Integer.compare(x,y);
    }


    @Test
    public void test5(){
//        String[] strs;
//        strs = {"aaa","bbb","ccc"};

        List<String> list = new ArrayList<String>();
        show(new HashMap<>());
    }

    public void show(Map<String, Integer> map){

    }


    //需求：对一个数进行运算
    @Test
    public void test6(){
        Integer value = operation(7, (x) -> x+5);
        System.out.println(value); //12
    }

    public Integer operation(Integer num, MyFun myFun) {
       Integer value = myFun.getValue(num);
       return value;
    }
}
