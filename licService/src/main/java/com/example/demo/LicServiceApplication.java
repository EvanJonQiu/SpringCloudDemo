package com.example.demo;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.ServiceConfig;
import com.example.demo.utils.UserContextInterceptor;
import com.netflix.discovery.shared.Application;

import redis.clients.jedis.JedisPoolConfig;

@SpringBootApplication
@EnableEurekaClient
public class LicServiceApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	@Autowired
	private ServiceConfig serviceConfig;
	
	@Bean
    public RestTemplate getRestTemplate() {
        RestTemplate template = new RestTemplate();
        List interceptors = template.getInterceptors();
        if (interceptors == null) {
            template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        } else {
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
        }

        return template;
    }
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(30);
		jedisPoolConfig.setMinIdle(10);
		
		JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfigurationBuilder = JedisClientConfiguration.builder();
		JedisClientConfiguration jedisClientConfiguration = jedisClientConfigurationBuilder.usePooling().poolConfig(jedisPoolConfig).build();
		
		
		RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(serviceConfig.getRedisServer());
        redisStandaloneConfiguration.setDatabase(0);
        redisStandaloneConfiguration.setPort(serviceConfig.getRedisPort());
        
        JedisConnectionFactory jedisConnFactory = new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
		return jedisConnFactory;
	}
	
	@Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setEnableTransactionSupport(true);
        return template;
    }
	
//	@StreamListener(Sink.INPUT)
//	public void loggerSink(OrganizationChangeModel orgChange) {
//		logger.debug("Received an event for organization id {}", orgChange.getOrganizationId());
//	}

	public static void main(String[] args) {
		SpringApplication.run(LicServiceApplication.class, args);
	}

}
