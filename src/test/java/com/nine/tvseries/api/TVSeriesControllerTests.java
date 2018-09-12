/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nine.tvseries.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TVSeriesControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testTVSeriesFilterWithSuccessResponse() throws Exception {

		String request = readFile("src/test/resources/requests/request-valid.json", StandardCharsets.UTF_8);
		this.mockMvc.perform(post("/").content(request).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.response[0].image")
						.value("http://mybeautifulcatchupservice.com/img/shows/16KidsandCounting1280.jpg"))
				.andExpect(jsonPath("$.response[0].slug").value("show/16kidsandcounting"))
				.andExpect(jsonPath("$.response[0].title").value("16 Kids and Counting"))
				.andExpect(jsonPath("$.response[0].country").doesNotExist())
				.andExpect(jsonPath("$.response").isArray())
				.andExpect(jsonPath("$.response.length()").value(7));
		
	}

	@Test
	public void testBadRequestResponse() throws Exception {

		String request = readFile("src/test/resources/requests/request-invalid.json", StandardCharsets.UTF_8);
		this.mockMvc.perform(post("/").content(request).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error")
						.value("Could not decode request: JSON parsing failed"));
		
	}
	
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
