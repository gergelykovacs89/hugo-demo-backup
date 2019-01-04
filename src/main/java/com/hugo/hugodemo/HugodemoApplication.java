package com.hugo.hugodemo;

import com.hugo.hugodemo.api.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class HugodemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(HugodemoApplication.class, args);
    }
}
