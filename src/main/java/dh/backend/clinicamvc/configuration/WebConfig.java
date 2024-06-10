package dh.backend.clinicamvc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    public void addCorsMapping(CorsRegistry registry){
        registry.addMapping("/**") // Allow all request
                .allowedOrigins("http://127.0.0.1:5500/") // Request origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Methods
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
