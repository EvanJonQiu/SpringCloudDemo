package com.example.demo.events.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.example.demo.events.models.OrganizationChangeModel;
import com.example.demo.utils.UserContext;

@Component
public class SimpleSourceBean {
	
	private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);
	
	private Source source;
	
	public SimpleSourceBean(Source source) {
		super();
		this.source = source;
	}
	
	public Source getSource() {
		return source;
	}
	
	public void setSource(Source source) {
		this.source = source;
	}
	
	public void publishOrgChange(String action, String orgId) {
		logger.debug("Sending Kafka message {} for Organization Id: {}", action, orgId);
		OrganizationChangeModel change =  new OrganizationChangeModel(
                OrganizationChangeModel.class.getTypeName(),
                action,
                orgId,
                UserContext.getCorrelationId());

        source.output().send(MessageBuilder.withPayload(change).build());
	}
}
