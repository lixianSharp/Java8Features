package org.lixianyuan.lambda.interfaceTest;

/**
 * @author lxy
 * @Date 2020/7/28
 * @Descript
 **/
public interface MyInterface {
    default String getName() {
        return "呵呵呵";
    }

    public static void show() {
        System.out.println("接口中的静态方法");
    }
}
