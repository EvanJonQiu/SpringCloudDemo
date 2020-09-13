package com.example.demo.repository;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Organization;

@Repository
public class OrganizationRedisRepositoryImpl implements OrganizationRedisRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(OrganizationRedisRepositoryImpl.class);
	
	private static final String HASH_NAME ="organization";
	private HashOperations hashOperations;
	
	private RedisTemplate<String, Organization> redisTemplate;
	public OrganizationRedisRepositoryImpl(){
        super();
    }
	
	@Autowired
    private OrganizationRedisRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
	
	@PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

	@Override
	public void saveOrganization(Organization org) {
		hashOperations.put(HASH_NAME, org.getId(), org);
	}

	@Override
	public void updateOrganization(Organization org) {
		hashOperations.put(HASH_NAME, org.getId(), org);
	}

	@Override
	public void deleteOrganization(String organizationId) {
		logger.debug("in OrganizationRedisRepositoryImpl, delete organization id: {}", organizationId);
		hashOperations.delete(HASH_NAME, organizationId);
	}

	@Override
	public Organization findOrganization(String organizationId) {
		return (Organization) hashOperations.get(HASH_NAME, organizationId);
	}

}
