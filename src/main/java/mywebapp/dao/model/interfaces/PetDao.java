//Interface for petDAO

package mywebapp.dao.model.interfaces;

import java.util.*;

import org.springframework.dao.DataAccessException;
import mywebapp.model.Pet;

public interface PetDao {
	
	//Get all pets, return the list of them:
	public List<Pet> getAllPets() throws DataAccessException;
	
	//Get all pets according to the id:
	public List<Pet> getPetsById(String id);
	
	//Get all pets according to the species:
	public List<Pet> getPetsBySpecies(String species);
	
	public List<Pet> getPetsByName(String name);
	
	public Pet insertPet(Map<String, String> petInfo);

}
