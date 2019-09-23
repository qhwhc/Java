package com.fast.aop.aspire.service;

import com.alibaba.fastjson.JSON;
import com.fast.aop.aspire.annotation.AdviceOne;
import com.fast.aop.aspire.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * aop测试
 *
 * @author JustryDeng
 * @date 2018/12/3 19:42
 */
@Service
public class AopService {

    @AdviceOne
    public Map<String, Object> test(User user) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " -> " + JSON.toJSONString(user));
        Map<String, Object> map = new HashMap<>(8);
        map.put("params", JSON.toJSONString(user));
        map.put("code", "0000");
        map.put("message", "成功");
        return map;
    }

}
