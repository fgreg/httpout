package httpout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:integration-context.xml")
public class HttpoutApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpoutApplication.class, args);
    }
}
