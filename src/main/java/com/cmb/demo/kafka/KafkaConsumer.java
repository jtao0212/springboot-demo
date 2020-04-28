package com.cmb.demo.kafka;

import com.alibaba.fastjson.JSON;
import com.cmb.demo.bean.Course;
import com.cmb.demo.bean.Student;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaConsumer {

    @Autowired
    private MongoTemplate mongoTemplate;

//    @KafkaListener(topics = "student1")
    public void onStudentMessage(ConsumerRecord consumerRecord) {
        Optional<Object> kafkaMassage = Optional.ofNullable(consumerRecord.value());
        if (kafkaMassage.isPresent()) {
            String msg = (String) kafkaMassage.get();
            Student student = JSON.parseObject(msg, Student.class);
            mongoTemplate.save(student);

        }
    }

//    @KafkaListener(topics = "course1")
    public void onCourseMessage(ConsumerRecord consumerRecord) {
        Optional<Object> kafkaMassage = Optional.ofNullable(consumerRecord.value());
        if (kafkaMassage.isPresent()) {
            String msg = (String) kafkaMassage.get();
            Course course = JSON.parseObject(msg, Course.class);
            mongoTemplate.save(course);
        }
    }

}
