package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api")
public class TestController {
	
	@Autowired
    private OrganizationService orgService;
	
	@Autowired
	private Producer producer;
	
	@RequestMapping(value="/hello",method = RequestMethod.GET)
    public String getTest() {
		producer.getSource().output().send(MessageBuilder.withPayload("hello").build());
        return "hello";
    }
	
	@RequestMapping(value="/{organizationId}",method = RequestMethod.GET)
    public Organization getOrganization( @PathVariable("organizationId") String organizationId) {
		producer.getSource().output().send(MessageBuilder.withPayload("hello").build());
		
        return orgService.getOrg(organizationId);
    }
}
