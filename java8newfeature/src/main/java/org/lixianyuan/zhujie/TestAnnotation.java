package org.lixianyuan.zhujie;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author lxy
 * @Date 2020/7/28
 * @Descript 重复注解与类型注解
 **/
public class TestAnnotation {

    //checker framework
    private /*@NonNull*/ Object obj = null;


    @Test
    public void test1() throws Exception{
        Class<TestAnnotation> clazz = TestAnnotation.class;
        Method m1 = clazz.getMethod("show");

        MyAnnotation[] mas = m1.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation myAnnotation : mas) {
            System.out.println(myAnnotation.value());
        }
        /**
         * 打印输出结果:
         * Hello
         * World
         */
    }

    @MyAnnotation("Hello")
    @MyAnnotation("World")
    public void show(@MyAnnotation("abc") String str) {

    }
}
