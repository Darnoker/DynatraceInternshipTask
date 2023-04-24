package internship.task.dynatracetask.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "nbp")
@Getter
@Setter
public class NbpConfig {

    private String apiUrl;
}
