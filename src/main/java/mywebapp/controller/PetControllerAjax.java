package mywebapp.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mywebapp.dao.model.interfaces.PetDao;
import mywebapp.model.Pet;

@RestController
@RequestMapping(value = "petJson.htm")
public class PetControllerAjax {
	
	private static final Logger LOG = LoggerFactory.getLogger(PetControllerAjax.class);
	
	public static Logger getLog() {
		return LOG;
	}
	
	@Autowired
	@Qualifier("PetDaoJpaImpl")
	private PetDao petDao;

	

	public PetDao getPetDao() {
		return petDao;
	}
	
	public void setPetDao(PetDao petDao) {
		this.petDao = petDao;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String getAllPets() throws IOException {
		getLog().info("REST Controller activating........");
		List<Pet> petList = getPetDao().getAllPets();
		ObjectMapper om = new ObjectMapper();
		String petListJson = om.writeValueAsString(petList);
		om.writeValue(System.out,petList);
		return petListJson;
	}

}
