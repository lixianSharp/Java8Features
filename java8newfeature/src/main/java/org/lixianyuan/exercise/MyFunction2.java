package org.lixianyuan.exercise;

/**
 * @author lxy
 * @Date 2020/5/8
 * @Descript
 **/
@FunctionalInterface
public interface MyFunction2<T,R> {
    public R getValue(T t1,R r1);
}
