package com.redis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/*
 * @author: Abin
 * @date: 2023/10/4 12:28
 * @description:
 */
public class JedisTest {
    private Jedis jedis;

    @BeforeEach
    void setUp(){
        // 1.建立redis连接
        //jedis = new Jedis("192.168.118.131", 6379);
        jedis = JedisConnectionFactory.getJedis();
        // 2.设置密码
        //jedis.auth("123321");
        // 3.选择一个库
        jedis.select(0);
    }

    @Test
    void testString(){
        // 存入数据
        String result = jedis.set("name", "huge");
        System.out.println("结果是：" + result);
        // 取出数据
        String name = jedis.get("name");
        System.out.println("name是：" + name);
    }

    @Test
    void testHash() {
        // 插入hash数据
        jedis.hset("user:1", "name", "Jack");
        jedis.hset("user:1", "age", "19");
        // 取出hash数据
        Map<String, String> stringStringMap = jedis.hgetAll("user:1");
        System.out.println("取出的map数据：" + stringStringMap);
    }

    @AfterEach
    void tearDown(){
        if(jedis != null){
            jedis.close();
        }
    }
}
