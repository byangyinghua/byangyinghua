package com.jjxx.testmybatis;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath*:config/spring-mybatis.xml"})
public class TestSpringDemo {
	private static Logger logger = Logger.getLogger(TestSpringDemo.class);
	

    
    @Test
    public void test1() {
       // User user = (User) userService.getObjectById(1);
        //logger.info("userName" + user.getUserName());
    }
	
}
