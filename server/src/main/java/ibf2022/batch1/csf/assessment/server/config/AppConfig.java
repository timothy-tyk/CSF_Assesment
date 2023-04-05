package ibf2022.batch1.csf.assessment.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class AppConfig {
  // MongoDB
  @Value("${mongo.url}")
  private String mongoUrl;

  private final String DATABASE_NAME="movies";

  @Bean
  public MongoTemplate mongoTemplate(){
    MongoClient client = MongoClients.create(mongoUrl);
    return new MongoTemplate(client, DATABASE_NAME);
  }
}
