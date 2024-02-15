package ru.est0y.diceChess.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration
@EnableWebMvc
public class WebConfig {
    @Bean
    public WebMvcConfigurer configurer () {
        return new WebMvcConfigurer() {

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/")
                        .setCacheControl(CacheControl.maxAge(Duration.ofDays(365)));

                registry.addResourceHandler("/js/**")
                        .addResourceLocations("classpath:/static/js/");

                registry.addResourceHandler("/webjars/**")
                       // .addResourceLocations("/webjars/");
                        .addResourceLocations("classpath:/META-INF/resources/webjars/")
                        .resourceChain(false);

            }
        };
    }
}
