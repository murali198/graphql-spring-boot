package com.murali.graphql.config;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class FeignConfig {

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

    @Bean
    public Encoder encoder() {
        return new JacksonEncoder();
    }

    @Bean
    public Decoder decoder() {
        return new JacksonDecoder();
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(300_000, TimeUnit.MILLISECONDS, 300_000, TimeUnit.MILLISECONDS, true);
    }

    @Bean
    public Retryer retryer() {
        return Retryer.NEVER_RETRY;
    }
}
