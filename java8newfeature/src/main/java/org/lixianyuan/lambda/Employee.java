package org.lixianyuan.lambda;

import lombok.Data;

import java.util.Objects;

/**
 * @author lxy
 * @Date 2020/5/6
 * @Descript
 **/
@Data
public class Employee {
    private String name;
    private int age;
    private double salary;
    private Status status;
    public Employee() {
    }

    public Employee(int age){
        this.age = age;
    }

    public Employee(String name){
        this.name = name;
    }
    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Employee(String name, int age, double salary, org.lixianyuan.lambda.Employee.Status status) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getAge() == employee.getAge() &&
                Double.compare(employee.getSalary(), getSalary()) == 0 &&
                Objects.equals(getName(), employee.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getSalary());
    }

    public enum Status{
        FREE, BUSY,VOCATION;
    }
}
