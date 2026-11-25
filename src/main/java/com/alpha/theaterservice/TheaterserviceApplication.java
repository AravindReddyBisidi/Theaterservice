package com.alpha.theaterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
public class TheaterserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheaterserviceApplication.class, args);
	}
	
	
	@Bean
	public RedisTemplate<String, String> redisTemplate(
	        RedisConnectionFactory connectionFactory) {

	    RedisTemplate<String, String> template = new RedisTemplate<>();

	    template.setConnectionFactory(connectionFactory);
	    template.setKeySerializer(new StringRedisSerializer());
	    template.setValueSerializer(new StringRedisSerializer());

	    return template;
	}

}
