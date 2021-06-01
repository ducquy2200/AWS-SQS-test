package com.springboot;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {ContextStackAutoConfiguration.class})
@RestController
public class AwsSqsTestApplication {

	@Autowired
	private QueueMessagingTemplate queueMessagingTemplate;
	
	@Value("${cloud.aws.end-point.uri}")
	private String endpoint;
	
	@GetMapping("/message/{message}")
	public String sendMessage(@PathVariable String message) {
		queueMessagingTemplate.send(endpoint, MessageBuilder.withPayload(message).build());
		return message;
	}
	
	@SqsListener("AWS-SQS-test")
	public void receiveMessage(String message) {
		LoggerFactory.getLogger(AwsSqsTestApplication.class).info("Message: \"{}\"", message);
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AwsSqsTestApplication.class, args);
	}

}
