package com.application;

import com.xcb.jedis.util.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

/*
 * @author: Abin
 * @date: 2023/2/13 20:22
 * @description:
 */
public class TestRedis {

    private Jedis jedis;

    //1.pom添加依赖
    //2.建立连接
    @BeforeEach
    void setUp() {
        //建立连接
        //jedis = new Jedis("192.168.118.131", 6379);
        //连接池连接jedis
        jedis = JedisConnectionFactory.getJedis();
        //设置密码
        jedis.auth("123321");
        //选择库
        jedis.select(0);
    }

    //3.操作String
    @Test
    void testString() {
        //添加数据 String 类型
        jedis.set("name001", "斌哥001");
        //获取数据
        String name001 = jedis.get("name001");
        System.out.println("name001 = " + name001);
        //添加数据 Hash 类型
        jedis.hset("user:001", "name001", "斌哥001");
        jedis.hset("user:001", "age001", "19");
        jedis.hset("user:001", "sex001", "男");
        //获取数据
        Map<String, String> hgetAll = jedis.hgetAll("user:001");
        System.out.println(hgetAll);
    }

    //4.释放资源
    @AfterEach
    void tearDown() {
        if(jedis != null) {
            jedis.close();
        }
    }
}
