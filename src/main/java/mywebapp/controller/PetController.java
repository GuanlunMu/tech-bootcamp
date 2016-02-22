package mywebapp.controller;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import mywebapp.dao.model.interfaces.PetDao;
import mywebapp.model.Pet;

@Controller
@RequestMapping
public class PetController {
	
	private static final Logger LOG = LoggerFactory.getLogger(PetController.class);

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

	@RequestMapping(value = "pet.htm", method = RequestMethod.GET)
	public ModelAndView getAllPets() throws Exception {
		getLog().info("Activating controller........");
		List<Pet> petList = getPetDao().getAllPets();
		getLog().info("Getting name in nameList......");
		return new ModelAndView("petInfo", "petList", petList);
	}

	@RequestMapping(value = "pet.htm", method = RequestMethod.POST)
	public ModelAndView getPetsByCriteria(@RequestParam Map<String, String> reqPar) {
		List<Pet> petList = new ArrayList<Pet>();
		String id = reqPar.get("id");
		String species = reqPar.get("species");
		String name = reqPar.get("name");
		getLog().info("The value for id and species are: " + id + " and" + species);
		if (!id.equals("")) {
			petList = getPetDao().getPetsById(id);
		} else if (!name.equals("")) {
			petList = getPetDao().getPetsByName(name);
		} else {
			petList = getPetDao().getPetsBySpecies(species);
		}
		return new ModelAndView("petInfo", "petList", petList);
	}

	@RequestMapping(value = "addPet.htm", method = RequestMethod.POST)
	public ModelAndView addPet(@RequestParam Map<String, String> reqPar) {
		Pet response = getPetDao().insertPet(reqPar);
		return new ModelAndView("addResults", "response", response);

	}

}
