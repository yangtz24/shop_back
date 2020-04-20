package com.ytz.shop.test;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: ClassA
 * @Description: 测试 事务
 * @author: yangtianzeng
 * @date: 2020/4/20 11:18
 */
@Service
@Transactional
public class ClassA {

    public Integer a() {
        try {
            System.out.println("a()  -------->   a()");
            int i = 10 / 0;
            return i;
        } catch (Exception e) {
            System.out.println("a()   Exception");
            e.printStackTrace();
            return null;
        }
    }

    public void b() {
        try {
            System.out.println("b()    ----------->    b()");
            Integer a = a();
            System.out.println("a---------------->" + a);
        } catch (Exception e) {
            System.out.println("b()   Exception");
            e.printStackTrace();
        }
    }
}
