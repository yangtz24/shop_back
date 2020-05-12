package com.ytz.shop;

import com.ytz.shop.test.ClassA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class DemoApplicationTests {

    static final Integer K = 1024;

    @Test
    void contextLoads() {

        /*LocalDateTime now = LocalDateTime.now(ZoneId.of("+8"));
        System.out.println(now.toString() + "\t  *************");
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String format = formatter.format(now);
        System.out.println(format + "\t +++++++++++++++");*/

        final LocalDateTime now = LocalDateTime.now(ZoneId.of("+8"));
        System.out.println(now);
    }

    @Autowired
    ClassA classA;

    @Test
    void test1() {
        classA.b();
    }

    @Test
    void test2() {
        int size = K * K * 8;
        List<byte[]> bytes = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            System.out.println("JVM 写入数据" + (i + 1) + "M");
            try { TimeUnit.MILLISECONDS.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
            bytes.add(new byte[size]);
        }
    }

}
