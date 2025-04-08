package messaging.ibm.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "messaging.ibm.api")
@EntityScan(basePackages = "messaging.ibm.api.model")
@EnableJpaRepositories(basePackages = "messaging.ibm.api")
public class MessagingIbmApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessagingIbmApiApplication.class, args);
    }

}
