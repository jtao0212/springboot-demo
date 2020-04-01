package com.cmb.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Scheduled(cron = "0/5 * * * * ?")
    public void scheduler(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(sdf.format(new Date())+"SchedulerConfig.scheduler");
    }
}
