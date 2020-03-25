package com.cloud.lab.management.config;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2020/3/25 09:08
 */
@Configuration
public class DockerClientConfig {

    public static final String URL = "http://192.168.43.128:4789";

    @Bean
    public DockerClient dockerClient() {
        return DefaultDockerClient.builder()
                .uri(URI.create(URL))
                .connectionPoolSize(100)
                .connectTimeoutMillis(1000 * 10)
                .build();
    }
}
