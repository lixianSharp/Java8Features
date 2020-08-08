package org.lixianyuan.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author lxy
 * @Date 2020/5/27
 * @Descript Fork/Join框架
 **/
public class ForkJoinCaculate extends RecursiveTask<Long> {

    private long start;
    private long end;

    private static final long THRESHOLD = 10000;

    public ForkJoinCaculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else{
            long middle = (start+end)/2;
            ForkJoinCaculate left = new ForkJoinCaculate(start,middle);
            left.fork();//拆分子任务，同时压入线程队列

            ForkJoinCaculate right = new ForkJoinCaculate(middle + 1, end);
            right.fork();

            return left.join()+right.join();
        }
    }
}
