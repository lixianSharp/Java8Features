package org.lixianyuan.lambda;

/**
 * @author lxy
 * @Date 2020/5/7
 * @Descript
 **/
@FunctionalInterface
public interface MyPredicated<T> {

    public abstract boolean test(T t);

}
