package com.cmb.demo.config;

import com.alibaba.fastjson.JSON;
import com.cmb.demo.bean.User;
import com.cmb.demo.kafka.KafkaProducer;
import com.cmb.demo.mapper.UserMapper;
import com.cmb.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Scheduled(cron = "0/30 * * * * ?")
    public void scheduler() {

        String key = "user";
        /*查询redis游标*/
        String time = (String) redisUtil.get(key);


        if (null == time) {
            time = "";
        }

        /*查询业务信息*/
        List<User> users = userMapper.getUserInfoByTime(time);

        String newTime = null;

        /*推送信息到kafka*/
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (i == users.size() - 1) {
                newTime = user.getTime().toString();
            }
            kafkaProducer.send(JSON.toJSONString(user));
        }
        /*保存新游标到redis*/
        if (null != newTime) {
            redisUtil.set(key, newTime);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(sdf.format(new Date()) + "SchedulerConfig.scheduler");
    }
}
