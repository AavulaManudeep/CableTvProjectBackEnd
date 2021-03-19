package com.cabletvbackend.CableTVProjectBackEnd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URL;

@SpringBootTest
@AutoConfigureMockMvc
public class CableTvProjectBackEndApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void test() throws Exception
	{
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(new URL("http://localhost:8089/controller/tester").toString())
				.accept(MediaType.ALL).content("Fun").contentType(MediaType.ALL);
		Assertions.assertEquals(HttpStatus.OK.value(),mockMvc.perform(requestBuilder).andReturn().getResponse().getStatus());

	}
}


