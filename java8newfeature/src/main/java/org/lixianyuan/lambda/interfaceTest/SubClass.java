package org.lixianyuan.lambda.interfaceTest;

/**
 * @author lxy
 * @Date 2020/7/28
 * @Descript
 **/
public class SubClass /*extends MyClass*/ implements MyFun2,MyInterface {
    @Override
    public String getName() {
        return MyFun2.super.getName();
    }
}
