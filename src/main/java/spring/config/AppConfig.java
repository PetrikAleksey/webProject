package spring.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(value= { "spring.*" })
@PropertySource(value = {
        "classpath:app.properties"
})
@Import(value = {
        DataConfig.class,
        WebConfig.class
})
public class AppConfig {

}
