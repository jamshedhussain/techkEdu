package com.itzst.tracktedu.controller.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itzst.tracktedu.controller.BatchController;
import com.itzst.tracktedu.model.Batch;
import com.itzst.tracktedu.service.BatchService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=BatchController.class)
public class BatchControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BatchService batchService; 
	
	@Test
	public void testCreateBatch()throws Exception {
		Batch mockBatch=new Batch();
		mockBatch.setBatchId("5askdff4fdf4fd45rr5");
		mockBatch.setSchoolId(1001L);
		mockBatch.setBatchCode("ABC");
		mockBatch.setBatchName("ABC Course");
		mockBatch.setBatchDescription("ABC Description");
		mockBatch.setIsActive(false);
		mockBatch.setBatchCreated(LocalDateTime.now());
		
		String inputInJson=this.mapToJson(mockBatch);
		
		String uri="/api/v1//batch";
		
		Mockito.when(batchService.createBatch(Mockito.any(Batch.class))).thenReturn(mockBatch);
		
		RequestBuilder requestBuilder=MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response=result.getResponse();
		
		String outpputInJson=response.getContentAsString();
		
		assertThat(outpputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(),response.getStatus());
		
	}//testCreateBatch
	
//method to convert String to json obj
	private String mapToJson(Object object) throws JsonProcessingException{
		ObjectMapper objectMapper=new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}//EndOfTestClass

