package com.piyush.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test; 
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class WebSeriesApplicationTests {
	
	@Autowired
    private MockMvc mockMvc;	

	//Add A New Series
	@Test
    public void TestCase1() throws Exception {
		
		String dataOne = "{\"id\":\"12881\",\"name\":\"Breaking Bad\",\"seasons\":\"5\",\"episodes\":\"62\",\"rating\":\"9\"}";
	 	mockMvc.perform(MockMvcRequestBuilders.post("/addSeries")
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.content(dataOne)
	 			.accept(MediaType.APPLICATION_JSON))
	        	.andExpect(status().isOk())
	        	.andReturn();
	 	
    }
	
	//Get A Ride
	@Test
    public void TestCase2() throws Exception {
		
	 	mockMvc.perform(MockMvcRequestBuilders.get("/series")
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.accept(MediaType.APPLICATION_JSON))
	        	.andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$[*].name").exists())
		        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
	        	.andReturn();
	 	
    }

	//Get A Ride by Id
	@Test
	public void TestCase3() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/series/id/12881")
				.contentType(MediaType.APPLICATION_JSON)
		 		.accept(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.name").value("Breaking Bad"))
		        .andExpect(jsonPath("$.seasons").value("5"))
		        .andExpect(jsonPath("$.episodes").value("62"))
		        .andExpect(jsonPath("$.rating").value("9"))
		        .andReturn();
		
	}
	
	//Get A Ride by Name
	@Test
	public void TestCase4() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/series/name/Breaking Bad")
				.contentType(MediaType.APPLICATION_JSON)
		 		.accept(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.name").value("Breaking Bad"))
		        .andExpect(jsonPath("$.seasons").value("5"))
		        .andExpect(jsonPath("$.episodes").value("62"))
		        .andExpect(jsonPath("$.rating").value("9"))
		        .andReturn();
		
	}	
	
	//Update the Series
	@Test
    public void TestCase5() throws Exception {
		
		String dataOne = "{\"id\":\"12881\",\"name\":\"Breaking Bad\",\"seasons\":\"5\",\"episodes\":\"63\",\"rating\":\"10\"}";
	 	mockMvc.perform(MockMvcRequestBuilders.put("/update")
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.content(dataOne)
	 			.accept(MediaType.APPLICATION_JSON))
	        	.andExpect(status().isOk())
	        	.andReturn();
	 	
	 	mockMvc.perform(MockMvcRequestBuilders.get("/series/id/12881")
				.contentType(MediaType.APPLICATION_JSON)
		 		.accept(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.name").value("Breaking Bad"))
		        .andExpect(jsonPath("$.seasons").value("5"))
		        .andExpect(jsonPath("$.episodes").value("63"))
		        .andExpect(jsonPath("$.rating").value("10"))
		        .andReturn();
	 	
    }
	
	//Delete the Series
	@Test
    public void TestCase6() throws Exception {
		
	 	mockMvc.perform(MockMvcRequestBuilders.delete("/delete/12881")
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.accept(MediaType.APPLICATION_JSON))
	        	.andExpect(status().isOk())
	        	.andExpect(jsonPath("$").value("Series Removed ! 12881"))
	        	.andReturn();
	 			
    }

}
