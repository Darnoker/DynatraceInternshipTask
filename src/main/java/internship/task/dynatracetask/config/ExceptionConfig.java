package internship.task.dynatracetask.config;

import internship.task.dynatracetask.exceptionhandler.HttpClientErrorExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionConfig {

    @Bean
    HttpClientErrorExceptionHandler httpClientErrorExceptionHandler() {
        return new HttpClientErrorExceptionHandler();
    }
}
