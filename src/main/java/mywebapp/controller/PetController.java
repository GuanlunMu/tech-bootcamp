package mywebapp.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
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

	@Autowired
	@Qualifier("PetDaoJpaImpl")
	private PetDao petDao;
	
	@Autowired
	private ResourceBundleMessageSource messageSource;

	public PetDao getPetDao() {
		return petDao;
	}

	public void setPetDao(PetDao petDao) {
		this.petDao = petDao;
	}

	@RequestMapping(value = "pet.htm", method = RequestMethod.GET)
	public ModelAndView getAllPets() throws Exception {
		System.out.println("Activating controller........");
		List<Pet> petList = getPetDao().getAllPets();
		System.out.println("Getting name in nameList......");
		System.out.println("Servelet returning following Pet......");
		for (Pet pet : petList) {
			System.out.println(pet.getName());
		}
		
		Locale en = new Locale("de");
		String userTitle = messageSource.getMessage("user.title", null, en);
		System.out.println("Showing i18n......." + userTitle);
		return new ModelAndView("petInfo", "petList", petList);
	}

	@RequestMapping(value = "pet.htm", method = RequestMethod.POST)
	public ModelAndView getPetsByCriteria(@RequestParam Map<String, String> reqPar) {
		List<Pet> petList = new ArrayList<Pet>();
		String id = reqPar.get("id");
		String species = reqPar.get("species");
		String name = reqPar.get("name");
		System.out.println("The value for id and species are: " + id + " and" + species);
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
