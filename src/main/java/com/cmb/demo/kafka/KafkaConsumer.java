package com.cmb.demo.kafka;

import com.alibaba.fastjson.JSON;
import com.cmb.demo.bean.Course;
import com.cmb.demo.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @Autowired
    private MongoTemplate mongoTemplate;

    @KafkaListener(topics = "student")
    public void onStudentMessage(String msg) {

        Student student = JSON.parseObject(msg, Student.class);
        mongoTemplate.save(student);
        System.out.println("kafka消费到的信息：" + student.toString());
    }

    @KafkaListener(topics = "course")
    public void onCourseMessage(String msg) {
        Course course = JSON.parseObject(msg, Course.class);
        mongoTemplate.save(course);
        System.out.println("kafka消费到的信息：" + course.toString());
    }

}
