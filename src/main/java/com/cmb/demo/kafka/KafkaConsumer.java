package com.cmb.demo.kafka;

import com.alibaba.fastjson.JSON;
import com.cmb.demo.bean.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "user")
    public void onMessage(String message) {
        User user = JSON.parseObject(message, User.class);
        System.out.println("kafka消费到的信息：" + user.toString());
    }

}
