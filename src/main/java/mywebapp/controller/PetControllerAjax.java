package mywebapp.controller;

import java.io.IOException;
import java.util.List;

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
		System.out.println("AJAX Controller activating........");
		List<Pet> petList = getPetDao().getAllPets();
		System.out.println("AJAX calls return the following pets:");
		for (Pet p: petList) {
			System.out.println(p.getName());
		}
		ObjectMapper om = new ObjectMapper();
		String petListJson = om.writeValueAsString(petList);
		om.writeValue(System.out,petList);
		return petListJson;
	}

}
