package com.cmb.demo.Scheduler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class MongoDbScheduler {

    private Logger logger = LoggerFactory.getLogger(MongoDbScheduler.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Scheduled(cron = "0/30 * * * * ？*")
    public void aggMongoData() {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.lookup("course", "studentId", "studentId", "course"));

        AggregationResults<Document> results = mongoTemplate.aggregate(agg, "student", Document.class);
        System.out.printf(results.toString());
    }
}
