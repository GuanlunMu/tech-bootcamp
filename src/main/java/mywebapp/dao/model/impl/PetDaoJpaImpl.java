package mywebapp.dao.model.impl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mywebapp.dao.model.interfaces.PetDao;
import mywebapp.model.Pet;

@Repository("PetDaoJpaImpl")
public class PetDaoJpaImpl implements PetDao {
	@PersistenceUnit
	private EntityManagerFactory emf;

	@Override
	public List<Pet> getAllPets() {
		EntityManager em = this.emf.createEntityManager();
		try {
			System.out.println("Getting single pet");
			Query query = em.createQuery("SELECT p FROM Pet p");
			List results = query.getResultList();
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
			System.out.println("Getting pets according to ID");
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
			System.out.println("Getting pets by species........");
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
			System.out.println("Getting pets by name........");
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
		newPet.setSex(petInfo.get("sex").substring(0,1));

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsed = null;
		try {
			parsed = formatter.parse(petInfo.get("birth"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		newPet.setBirth(parsed);

		try {
			System.out.println("Inserting pet........");
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
