package org.lixianyuan.lambda;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.swing.text.html.Option;
import java.security.MessageDigest;
import java.security.Security;
import java.util.Optional;

/**
 * @author lxy
 * @Date 2020/7/28
 * @Descript
 **/
public class TestOptional {


    /**
     Optional 容器类的常用方法：
     Optional.of(T t): 创建一个Option实例
     Optional.empty(): 创建一个空的Optional实例
     Optional.ofNullable(T t): 若t不为null，创建Optional实例，否则创建空实例。
     isPresent():判断是否包含值
     orElese(T t): 如果调用对象包含值，返回该值，否则返回t
     orElseGet(Supplier s): 如果调用对象包含之，返回该值，否则返回s获取的值
     map(Function f):如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
     flatMap(Function mapper):与map类似，要求返回值必须是Optional
     */
    @Test
    public void test(){
        Optional<Employee> op = Optional.of(new Employee());//如果Optional.of(null);会抛出空指针异常
        Employee emp = op.get();
        System.out.println(emp);//Employee{name='null', age=0, salary=0.0, status=null}
    }

    @Test
    public void test2(){
        Optional<Object> op = Optional.empty();
        System.out.println(op.get());//java.util.NoSuchElementException: No value present
    }

    @Test
    public void test3(){
        Optional<Employee> op = Optional.ofNullable(new Employee());
//        if (op.isPresent()){
//            //为true，表示包含值
//            System.out.println(op.get());//Employee{name='null', age=0, salary=0.0, status=null}
//        }else{
//            //为false，表示不包含值
//        }

//        Employee emp = op.orElse(new Employee("张三", 18, 999.8, Employee.Status.FREE));
//        System.out.println(emp);
        Employee employee = op.orElseGet(() -> new Employee());
        System.out.println(employee);//Employee{name='null', age=0, salary=0.0, status=null}
    }

    @Test
    public void test4() {
        Optional<Employee> op = Optional.ofNullable(new Employee("张三", 18, 999.8, Employee.Status.FREE));
//        Optional<String> str = op.map((e) -> e.getName());
//        System.out.println(str.get());//张三

        Optional<String> str2 = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(str2.get());//张三

    }

    @Test
    public void test5() {
//        Man man = new Man();
//        String n = getGodnessName(man);
//        System.out.println(n);

        Optional<Godness> gn = Optional.ofNullable(new Godness("小黑"));
        Optional<NewMan> op = Optional.ofNullable(new NewMan(gn));
        String str = getGodenessName2(op);
        System.out.println(str);
    }

    public String getGodenessName2(Optional<NewMan> man) {
        return man.orElse(new NewMan())
                .getGodness()
                .orElse(new Godness("小小"))
                .getName();
    }

    //需求：获取一个man中的一个woman的名字
    private String getGodnessName(Man man) {
        if(man !=null){
            Godness gn = man.getGodness();
            if (gn != null) {
                return gn.getName();
            }
        }
        return "zhangsanwoman";
    }


    public static void main(String[] args) throws Exception {
        //"猪八戒" 的utf-8的md4亿次值
        Security.addProvider(new BouncyCastleProvider());
        String str = "猪八戒";
        MessageDigest md = MessageDigest.getInstance("MD4");
        for (int i = 0; i < 100000000; i++) {
            byte[] digest = md.digest(str.getBytes("UTF-8"));
            str = new String(Hex.encode(digest));
        }
        System.out.println(str);
    }

}
