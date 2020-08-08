package org.lixianyuan.lambda.interfaceTest;

/**
 * @author lxy
 * @Date 2020/7/28
 * @Descript
 **/
public class TesstDefaultInterface {
    public static void main(String[] args) {
        SubClass sc = new SubClass();
        System.out.println(sc.getName());

        MyInterface.show();//接口中的静态方法
    }
}
