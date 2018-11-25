package com.springboot.controller;

import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.BitSet;


/**
 * 缓存数据库测试
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedissonClient redissonClient;


    /**
     * 文档参考地址
     *  https://www.aliyun.com/jiaocheng/1450213.html
     * @return
     */
    @GetMapping("/test")
    public Object test() {
        RKeys rKeys = redissonClient.getKeys();
        RMap<String, String> map = redissonClient.getMap("name");
        map.fastPut("test1", "dddd");
        System.out.println(map.get("name"));
        System.out.println(rKeys.count());
        System.out.println(redissonClient);
        RBucket bucket = redissonClient.getBucket("hello");
        System.out.println(bucket.get());
        return "";
    }

    /**
     * redisson 对字符串对常用操作
     */
    @GetMapping("/string")
    public Object string(String name, String value) {

        RBucket bucket = redissonClient.getBucket(name);

        bucket.set(value);
        System.out.println(bucket.isExists());
        /***
         bucket.get() // 获取值
         bucket.set("value"); 设置值
         */
        return bucket.get();
    }

    /**
     * redisson 对 set集合对操作
     */

    @GetMapping("/set")
    public Object set(String name){
        RList list = redissonClient.getList(name);
        list.add("1");
        list.add("2");
        list.add("3");
        return "";
    }

    /**
     * redisson 获取 RSortedSet 有序集合
     */
    @GetMapping("/sortSet")
    public Object sortSet(String name) {
        RSortedSet<String> sortedSet = redissonClient.getSortedSet(name);
        sortedSet.add("v1");
        sortedSet.add("v2");
        sortedSet.add("v3");
        sortedSet.add("v4");
        return "";
    }
}

