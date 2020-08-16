package com.example.demo.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Organization;
import com.example.demo.repository.OrganizationRepository;

@Service
public class OrganizationService {
	@Autowired
    private OrganizationRepository orgRepository;
	
	public Organization getOrg(String organizationId) {
		Optional<Organization> op = orgRepository.findById(organizationId);
        return op.get();
    }
	
	public void saveOrg(Organization org){
        org.setId(UUID.randomUUID().toString());
        orgRepository.save(org);
    }
	
	public void updateOrg(Organization org){
        orgRepository.save(org);
    }
	
	public void deleteOrg(Organization org){
        orgRepository.deleteById(org.getId());
    }
}
