package com.kb.treatment;

import com.kb.treatment.configuration.RedisConfiguration.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TreatmentApplicationTests {

    @Autowired
    private RedisService redisService;


    @Test
    public void contextLoads() {

        redisService.set("hehe", "xixi");
        Object obj = redisService.get("hehe");
        System.out.println("-----------------------------------");
        System.out.println( obj );
    }

}
