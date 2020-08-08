package org.lixianyuan.lambda;


import org.junit.Test;

import java.util.*;

/**
 * @author lxy
 * @Date 2020/5/7
 * @Descript
 **/
public class TestLambda {

    //原来的匿名内部类
    @Test
    public void test1() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(com);

    }


    //lambda表达式
    @Test
    public void test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> ts = new TreeSet<>(com);

    }

    List<Employee> employeeList = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 5555.99),
            new Employee("王五", 50, 6666.66),
            new Employee("赵六", 16, 3333.33),
            new Employee("田七", 8, 7777.77)
    );

    //需求，获取当前公司中员工年龄大于35的员工信息
    @Test
    public void test3() {
        List<Employee> list = filterEmployees(employeeList);
        for (Employee emp : list) {
            System.out.println(emp);
        }
    }

    public List<Employee> filterEmployees(List<Employee> list) {
        List<Employee> emps = new ArrayList<Employee>();
        for (Employee emp : list) {
            if (emp.getAge() >= 35) {
                emps.add(emp);
            }
        }

        return emps;
    }

    //需求：获取员工工资大于5000的员工信息
    public List<Employee> filterEmployees2(List<Employee> list) {
        List<Employee> emps = new ArrayList<Employee>();
        for (Employee emp : list) {
            if (emp.getSalary() > 5000) {
                emps.add(emp);
            }
        }

        return emps;
    }

    //优化方式一：策略设计模式
    @Test
    public void test4() {
        //获取员工年龄大于35的员工信息
        List<Employee> list = filterEmployee(employeeList, new FilterEmployeeByAge());
        for (Employee emp : list) {
            System.out.println(emp);
        }

        System.out.println("----------------------------------------");
        //获取员工工资大于5000的员工信息
        List<Employee> list2 = filterEmployee(employeeList, new FilterEmployeeBySalary());
        for (Employee emp : list2) {
            System.out.println(emp);
        }

    }

    public List<Employee> filterEmployee(List<Employee> list, MyPredicated<Employee> mp) {
        List<Employee> emps = new ArrayList<Employee>();
        for (Employee employee : list) {
            if (mp.test(employee)) {
                emps.add(employee);
            }
        }
        return emps;
    }

    //优化方式二：匿名内部类
    @Test
    public void test5() {
        List<Employee> list = filterEmployee(employeeList, new MyPredicated<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() <= 5000;
            }
        });

        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    //优化方式三：Lambda表达式
    @Test
    public void test6() {
        List<Employee> list = filterEmployee(employeeList, emp -> emp.getSalary() >= 5000);
        //遍历list集合中的内容(使用Lambda表达式遍历list集合)
        list.forEach(System.out::println);
        /**
         * Employee{name='张三', age=18, salary=9999.99}
         * Employee{name='李四', age=38, salary=5555.99}
         * Employee{name='王五', age=50, salary=6666.66}
         * Employee{name='田七', age=8, salary=7777.77}
         */
    }

    //优化方式四：Stream API
    @Test
    public void test7(){
        employeeList.stream()  //将该employeeList集合作为一个连续的流
                .filter((e)->e.getSalary()>=5000)   //对该流进行过滤
                .limit(2)   //将该流进行截断，返回不超过流中的 两个元素
                .forEach(System.out::println);   //对过滤后的数据进行遍历输出

        System.out.println("------------------------------------------");

        employeeList.stream()
                .map(Employee::getName)
                .limit(4)
                .sorted()
                .forEach(System.out::println);
    }



}
