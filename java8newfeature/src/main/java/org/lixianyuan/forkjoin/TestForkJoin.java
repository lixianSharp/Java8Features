package org.lixianyuan.forkjoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author lxy
 * @Date 2020/5/28
 * @Descript
 **/
public class TestForkJoin {

    @Test
    public void test1() {
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCaculate(0, 1000000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println("耗费时间为：" + Duration.between(start, end).toMillis() + "毫秒");//166左右
    }

    //普通for
    @Test
    public void test2() {
        Instant start = Instant.now();
        long sum = 0L;
        for (long i = 0; i < 1000000000L; i++) {
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗费时间为：" + Duration.between(start, end).toMillis() + "毫秒");//260左右

    }

    /**
     * java8并行流
     *  并行流就是把一个内容分成多个数据块，并用不同的线程分别处理每个数据块的流。
     *  Stream API 可以声明性地通过parallel()与 sequential() 在并行流与顺序刘之间进行切换。
     */
    @Test
    public void test3() {
        Instant start = Instant.now();

        LongStream.rangeClosed(0, 10000000000L)
                .parallel()
                .reduce(0,Long::sum);

        Instant end = Instant.now();
        System.out.println("耗费时间为:"+Duration.between(start,end).toMillis());//817

    }
}
