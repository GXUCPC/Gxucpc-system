package cn.edu.gxu.gxucpcsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling //定时器注解
@EnableMongoRepositories("cn.edu.gxu.gxucpcsystem.dao.mongodb")
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class GxucpcSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(GxucpcSystemApplication.class, args);
	}
}
