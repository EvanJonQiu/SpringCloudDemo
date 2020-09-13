package com.example.demo.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.clients.OrganizationRestTemplateClient;
import com.example.demo.config.ServiceConfig;
import com.example.demo.model.License;
import com.example.demo.model.Organization;
import com.example.demo.repository.LicenseRepository;

@Service
public class LicenseService {
	private static final Logger logger = LoggerFactory.getLogger(LicenseService.class);
	
	@Autowired
	private LicenseRepository licenseRepository;
	
	@Autowired
    ServiceConfig config;
	
	@Autowired
    OrganizationRestTemplateClient organizationRestClient;
	
	public List<License> getLicensesByOrg(String organizationId){
		return licenseRepository.findByOrganizationId( organizationId );
	}
	
	public License getLicense(String organizationId,String licenseId) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);

        Organization org = getOrganization(organizationId);

        return license
                .withOrganizationName( org.getName())
                .withContactName( org.getContactName())
                .withContactEmail( org.getContactEmail() )
                .withContactPhone( org.getContactPhone() )
                .withComment(config.getExampleProperty());
    }
	
	private Organization getOrganization(String organizationId) {
        return organizationRestClient.getOrganization(organizationId);
    }
}
