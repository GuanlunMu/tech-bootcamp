package mywebapp.dao.model.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import mywebapp.dao.model.interfaces.PetDao;
import mywebapp.model.Owner;
import mywebapp.model.Pet;
import mywebapp.model.Toy;

@Repository("PetDaoJpaImpl")
public class PetDaoJpaImpl implements PetDao {

	static final private Logger LOG = LoggerFactory.getLogger(PetDaoJdbcImpl.class);

	public static Logger getLog() {
		return LOG;
	}

	@PersistenceUnit
	private EntityManagerFactory emf;

	@Override
	public List<Pet> getAllPets() {
		EntityManager em = this.emf.createEntityManager();
		try {
			getLog().info("Getting single pet");
			Query query = em.createQuery("SELECT p FROM Pet p");
			System.out.println("Getting all Results.....");
			List<Pet> results = query.getResultList();
			return results;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public List<Pet> getPetsById(String id) {
		int idInt = Integer.parseInt(id);
		EntityManager em = this.emf.createEntityManager();
		try {
			getLog().info("Getting pets according to ID");
			Query query = em.createQuery("SELECT p FROM Pet p WHERE p.id = :id").setParameter("id", idInt);
			List results = query.getResultList();
			return results;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public List<Pet> getPetsBySpecies(String species) {
		EntityManager em = this.emf.createEntityManager();
		try {
			getLog().info("Getting pets by species........");
			Query query = em.createQuery("SELECT p FROM Pet p WHERE p.species = :species").setParameter("species",
					species);
			List results = query.getResultList();
			return results;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public List<Pet> getPetsByName(String name) {
		EntityManager em = this.emf.createEntityManager();
		try {
			getLog().info("Getting pets by name........");
			Query query = em.createQuery("SELECT p FROM Pet p WHERE p.name = :name").setParameter("name", name);
			List results = query.getResultList();
			return results;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public Pet insertPet(Map<String, String> petInfo) {
		EntityManager em = this.emf.createEntityManager();
		Pet newPet = new Pet();

		newPet.setName(petInfo.get("name"));
		newPet.setOwner(petInfo.get("owner"));
		newPet.setSpecies(petInfo.get("species").toLowerCase());
		newPet.setSex(petInfo.get("sex").substring(0, 1));

		Toy toy1 = new Toy();
		toy1.setName("Sandy");
		toy1.setType("biting");
		
		Toy toy2 = new Toy();
		toy2.setName("Wheel");
		toy2.setType("running");
		
		Set<Toy> toys = new HashSet<Toy>();
		toys.add(toy1);
		toys.add(toy2);
		newPet.setToys(toys);
		
		Owner ownerDetail = new Owner();
		ownerDetail.setName(petInfo.get("owner"));
		ownerDetail.setAge(23);
		newPet.setOwnerDetail(ownerDetail);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsed = null;
		try {
			parsed = formatter.parse(petInfo.get("birth"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		newPet.setBirth(parsed);

		try {
			getLog().info("Inserting pet........");
			em.getTransaction().begin();
			em.persist(newPet);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return newPet;
	}

}
