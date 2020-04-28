package com.cmb.demo.Scheduler;

import com.alibaba.fastjson.JSON;
import com.cmb.demo.bean.Course;
import com.cmb.demo.bean.Student;
import com.cmb.demo.kafka.KafkaProducer;
import com.cmb.demo.mapper.CourseMapper;
import com.cmb.demo.mapper.StudentMapper;
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
public class DataLinkScheduler {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseMapper courseMapper;

    private static final String studentKey = "student";

    private static final String courseKey = "course";

    @Scheduled(cron = "0/30 * * * * ？*")
    public void doRefactor() {

        /*查询redis游标*/
        String studentTime = null == redisUtil.get(studentKey) ? "" : (String) redisUtil.get(studentKey);

        String courseTime = null == redisUtil.get(courseKey) ? "" : (String) redisUtil.get(courseKey);


        /*查询业务信息*/
        List<Course> courses = courseMapper.getCourseByTime(courseTime);

        List<Student> students = studentMapper.getStudentByTime(studentTime);

        String newCourseTime = null;

        String newStudentTime = null;

        /*推送信息到kafka*/
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            if (i == courses.size() - 1) {
                newCourseTime = course.getLastModifiedTime().toString();
            }
            kafkaProducer.send(JSON.toJSONString(course), "course");
        }
        /*保存新游标到redis*/
        if (null != newCourseTime) {
            redisUtil.set(courseKey, newCourseTime);
        }

        /*推送信息到kafka*/
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (i == students.size() - 1) {
                newStudentTime = student.getLastModifiedTime().toString();
            }
            kafkaProducer.send(JSON.toJSONString(student), "student");
        }
        /*保存新游标到redis*/
        if (null != newStudentTime) {
            redisUtil.set(studentKey, newStudentTime);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(sdf.format(new Date()) + "SchedulerConfig.scheduler");
    }
}
