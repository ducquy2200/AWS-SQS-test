package com.springboot;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AwsSqsTestApplication.class)
@AutoConfigureMockMvc
class AwsSqsTestApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void sendMessageTesting() throws Exception{
		String url = "/message/";
		String[] test = {"hello","test1", "test2", "test3", "test4", "test5","hello","test1", "a", "s"};
		for(int i = 0; i < 10; i++) {
			MvcResult mvcResult = mockMvc
				    .perform(MockMvcRequestBuilders.get(url+test[i]))
				    .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			Thread.sleep(1000);
			Assert.assertEquals(test[i], mvcResult.getResponse().getContentAsString());
		}
	}
	
//	@Test
//	public void receiveMessageTesting() throws Exception{
//		String url = "/message/";
//		String[] test = {"hello","test1", "test2", "test3", "test4", "test5","hello","test1", "a", "s"};
//		for(int i = 0; i < 10; i++) {
//			mockMvc.perform(MockMvcRequestBuilders.get(url+test[i])).andExpect(MockMvcResultMatchers.status().isOk());
//			Assert.assertEquals(test[i], outContent.toString());
//			Thread.sleep(2000);
//		}	
//	}
	
	@Test
	public void contextLoads() {	
	}

}
