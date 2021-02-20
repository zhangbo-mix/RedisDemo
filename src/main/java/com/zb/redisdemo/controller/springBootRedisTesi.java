package com.zb.redisdemo.controller;

import com.zb.redisdemo.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 超卖问题
 * 解决方法：redis的setnx方法
 */
@RestController
public class springBootRedisTesi {

    @Autowired
    RedisTemplate redisTemplate;


    @GetMapping(value = "/deduct")
    public String deductStock(){
        String returnResult = null;

        //redisTemplate.opsForValue().set("list",list());
        //redisTemplate.opsForValue().set("map",map());
        //redisTemplate.opsForValue().set("set",set());
        //redisTemplate.opsForValue().set("stock",100);
        String lokKey = "lockKey";
        //相当于jedis.setnx(key,value)//设置锁
        Boolean relult =  redisTemplate.opsForValue().setIfAbsent(lokKey,"zhang",60, TimeUnit.SECONDS);
        if (!relult){
            return  "error";
        }

        int stock = Integer.parseInt(redisTemplate.opsForValue().get("stock").toString());//相当于jedis.get("stock")

        if (stock > 0){
            int realStock = stock-1;
            redisTemplate.opsForValue().set("stock",realStock+"");//相对与jedis.set(key,value);
            System.out.println("扣减成功，剩余库存："+realStock);
            returnResult = "扣减成功，剩余库存："+realStock;
        }else{
            System.out.println("扣减失败，库存不足");
            returnResult = "扣减失败，库存不足";
        }
        redisTemplate.delete(lokKey);//删除锁
        return returnResult;
    }


    public List<String> list(){
        List<String> list = new ArrayList<>();
        for (int i = 0;i<10;i++){
            list.add(String.valueOf(i));
        }
        return list;
    }


    public Map<String,String> map(){
        Map<String,String> result = new HashMap<>();
        for (int i = 0;i<10;i++){
            result.put("序号"+i,String.valueOf(i));
        }
        return result;
    }

    public Set<String> set(){
        Set<String> set = new LinkedHashSet<>();
        for (int i = 0;i<10;i++){
            set.add("序号"+i);
        }
        return set;
    }


}
