package org.lixianyuan.lambda;

/**
 * @author lxy
 * @Date 2020/5/7
 * @Descript
 **/
public class FilterEmployeeByAge implements MyPredicated<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getAge()>35;
    }
}
