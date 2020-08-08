package org.lixianyuan.date;

import org.junit.Test;

import java.time.*;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * @author lxy
 * @Date 2020/7/28
 * @Descript
 **/
public class TestLocalDateTime {


    @Test
    public void test5(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);//2020-07-28T16:47:25.798

        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);//2020-07-10T16:47:25.798
        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);//2020-08-02T16:47:25.798

        //自定义：下一个工作日
//        LocalDateTime ldt5 =  ldt.with((1)->{
//            LocalDateTime ldt4 = (LocalDateTime)1;
//            DayOfWeek dow = ldt2.getDayOfWeek();
//            if (dow.equals(DayOfWeek.FRIDAY)) {
//                return ldt4.plusDays(3);
//            } else if (dow.equals(DayOfWeek.SATURDAY)) {
//                return ldt4.plusDays(2);
//            }else{
//                return ldt4.plusDays(1);
//            }
//        });
//        System.out.println(ldt5);
    }
    /**
     * 3、Duration:计算两个"时间"之间的间隔
     * Period:计算两个"日期"之间的间隔
     */
    @Test
    public void test4(){
        LocalDate ld1 = LocalDate.of(2015, 1, 1);
        LocalDate ld2 = LocalDate.now();
        Period period = Period.between(ld1, ld2);
        System.out.println(period);//P5Y6M27D

        System.out.println(period.getYears());//5
        System.out.println(period.getMonths());//6
        System.out.println(period.getDays());//7

        //当前的时间是: 2020年7月28号
    }


    @Test
    public void test3() {
        Instant ins1 = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Instant ins2 = Instant.now();

        Duration duration = Duration.between(ins1, ins2);
        System.out.println(duration.toMillis());
        System.out.println("-----------------------------");
        LocalTime lt1 = LocalTime.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime lt2 = LocalTime.now();
        System.out.println(Duration.between(lt1,lt2).toMillis());
    }

    //2、Instant:时间戳(以Unix元年：1970年1月1日 00:00:00 到某个时间之间的毫秒值)
    @Test
    public void test2() {
        Instant ins1 = Instant.now();//默认获取UTC时区
        System.out.println(ins1);//2020-07-28T06:25:23.454Z

        OffsetDateTime odt = ins1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);//2020-07-28T14:25:23.454+08:00

        System.out.println(ins1.toEpochMilli());//1595917523454

        Instant ins2 = Instant.ofEpochSecond(1000);
        System.out.println(ins2);//1970-01-01T00:16:40Z
    }

    //1、LocalDate  LocalTime  LocalDateTime
    @Test
    public void test1() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);//2020-07-28T14:19:32.194

        LocalDateTime ldt2 = LocalDateTime.of(2020, 10, 19, 13, 22, 33);
        System.out.println(ldt2);//2020-10-19T13:22:33

        LocalDateTime ldt3 = ldt.plusYears(2);
        System.out.println(ldt3);//2020-07-28T14:19:32.194  年份增加2年

        LocalDateTime ldt4 = ldt.minusMonths(2);//月份减少2个月
        System.out.println(ldt4);//2020-05-28T14:19:32.194

        //获取年月日时
        System.out.println(ldt.getYear());//2020
        System.out.println(ldt.getMonthValue());//7
        System.out.println(ldt.getDayOfMonth());//28
        System.out.println(ldt.getHour());//14
    }
}
