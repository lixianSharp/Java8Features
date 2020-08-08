package org.lixianyuan.exercise;

import org.junit.Test;
import org.lixianyuan.lambda.Employee;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author lxy
 * @Date 2020/5/8
 * @Descript
 *  1、调用Collections.sort()方法，通过定制排序比较两个Employee(先按年龄比，年龄相同按姓名比)，使用Lambda作为参数传递。
 *  2、 2.1生命函数式接口，接口中声明抽象方法，public String getValue(String str);
 *      2.2 声明类 TestLambda,类中编写方法使用接口作为参数，将一个字符串转换成大写，并作为方法的返回值。
 *      2.3在将一个字符串的第2个和第4个索引位置进行截取子串。
 *
 *  3、 3.1声明一个戴两个泛型的函数式接口，泛型类型为<T,R> , T为参数，R为返回值
 *      3.2 接口中声明对应抽象方法
 *      3.3在TestLambda类中声明方法，使用接口作为参数，计算两个long类型参数的和
 *      3.4再甲酸两个long型参数的乘积
 **/
public class TestLambda {

    List<Employee> emps = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 5555.99),
            new Employee("王五", 50, 6666.66),
            new Employee("赵六", 16, 3333.33),
            new Employee("田七", 8, 7777.77)
    );

    @Test
    public void test1(){
        Collections.sort(emps,(e1,e2)->{
            if(e1.getAge()>e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else{
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });

        for (Employee emp:emps){
            System.out.println(emp);
        }
    }

    @Test
    public void test2(){
        String trimStr = strHandler("\t\t\t\t 你好哈哈哈", (str) -> str.trim());
        System.out.println(trimStr);
        String upStr = strHandler("abcdefgh", (str) -> str.toUpperCase());
        System.out.println(upStr);
    }
    //需求：用于处理字符串
    public String strHandler(String str,MyFunction mf){
        return mf.getValue(str);
    }


    @Test
    public void test3(){
        op(100L,200L,(x,y)->x+y);
        op(100L,200L,(x,y)->x*y);
    }
    //需求：对于两个Long类型的数据进行处理
    public void op(Long l1,Long l2,MyFunction2<Long,Long> mf2){
        System.out.println(mf2.getValue(l1,l2));
    }
}
