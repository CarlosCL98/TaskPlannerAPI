package edu.eci.TaskPlanner.Persistence;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@Configuration
public class AppConfiguration {

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://TaskPlannerUser:WXkvdHgIyQfvWBtn@cluster0-mdbpa.mongodb.net/test?retryWrites=true&w=majority");
        MongoClient mongoClient = new MongoClient(uri);
        return new SimpleMongoDbFactory( mongoClient, "test");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        MongoTemplate mongoTemplate = mongoTemplate();
        return new GridFsTemplate(mongoDbFactory(), mongoTemplate.getConverter());
    }
}
