package com.example.demo.services;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.events.source.SimpleSourceBean;
import com.example.demo.model.Organization;
import com.example.demo.repository.OrganizationRepository;

@Service
public class OrganizationService {
	private static final Logger logger = LoggerFactory.getLogger(OrganizationService.class);
	
	@Autowired
    private OrganizationRepository orgRepository;
	
	@Autowired
    SimpleSourceBean simpleSourceBean;
	
	public Organization getOrg(String organizationId) {
		Optional<Organization> op = orgRepository.findById(organizationId);
		
		logger.info("recieve organization id:" + organizationId);
		simpleSourceBean.publishOrgChange("query", organizationId);
        return op.get();
    }
	
	public void saveOrg(Organization org){
        org.setId(UUID.randomUUID().toString());
        orgRepository.save(org);
    }
	
	public void updateOrg(Organization org){
        orgRepository.save(org);
    }
	
	public void deleteOrg(String ordId){
		logger.debug("in OrganizationService delete organization: {}", ordId);
        orgRepository.deleteById(ordId);
        simpleSourceBean.publishOrgChange("DELETE", ordId);
    }
}
