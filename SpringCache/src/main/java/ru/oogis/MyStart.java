package ru.oogis;

import com.google.common.cache.CacheBuilder;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableCaching
public class MyStart {
    public static void main(String[] args) {
        SpringApplication.run(MyStart.class, args);
    }


    @Bean
    public CacheManager cacheManager(){
        return new ConcurrentMapCacheManager("myCach"){
            @Override
            protected Cache createConcurrentMapCache(String name) {
                return new ConcurrentMapCache(
                        name,
                        CacheBuilder.newBuilder()
                        .expireAfterWrite(20, TimeUnit.SECONDS)
                        .maximumSize(2).build().asMap()
                        , false

                );
            }
        };
    }

}
