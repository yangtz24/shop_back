package com.ytz.shop;

import com.ytz.shop.test.ClassA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootTest
class DemoApplicationTests {

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

}
