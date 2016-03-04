package mywebapp.test.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import mywebapp.config.MywebappConfig;
import mywebapp.controller.PetControllerAjax;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MywebappConfig.class)
@WebAppConfiguration
public class PetControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private PetControllerAjax pcRest;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(pcRest).build();
	}

	@Test
	public void controllerShouldReturnRightData() throws Exception {

		RequestBuilder mockGet = MockMvcRequestBuilders.get("/petJson.htm");

		ResultActions response = mockMvc.perform(mockGet);
		response.andDo(MockMvcResultHandlers.print())
				.andExpect(status().is(200))
				.andExpect(jsonPath("$[0].id").isNumber())
				.andExpect(jsonPath("$[0].birth").exists())
				.andExpect(jsonPath("$[0].ownerDetail").exists());
		}

}