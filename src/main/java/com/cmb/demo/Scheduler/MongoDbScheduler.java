package com.cmb.demo.Scheduler;


import com.cmb.demo.kafka.KafkaProducer;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class MongoDbScheduler {

    private Logger logger = LoggerFactory.getLogger(MongoDbScheduler.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Scheduled(cron = "0/30 * * * * ？*")
    public void aggMongoData() {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.lookup("course", "studentId", "studentId", "course"));

        AggregationResults<Document> aggResults = mongoTemplate.aggregate(agg, "student", Document.class);
        List<Document> docResults = aggResults.getMappedResults();

        for (Document document : docResults) {
            String res =   document.toJson();
            kafkaProducer.send(res, "school1");
            logger.info("生产者生产消息：" + document.toString());
        }
    }
}
