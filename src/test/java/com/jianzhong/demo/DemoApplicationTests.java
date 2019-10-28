package com.jianzhong.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Before
    public void init(){
        System.out.println("开始测试-----------------");
    }

    @After
    public void after(){
        System.out.println("测试完成-----------------");
    }

}
