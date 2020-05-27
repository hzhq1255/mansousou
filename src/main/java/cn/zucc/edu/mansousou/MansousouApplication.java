package cn.zucc.edu.mansousou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = {"cn.zucc.edu.mansousou.repository.jpa"})
@EnableElasticsearchRepositories(basePackages = {"cn.zucc.edu.mansousou.repository.es"})
public class MansousouApplication {

    public static void main(String[] args) {
        SpringApplication.run(MansousouApplication.class, args);
    }

}
