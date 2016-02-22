package mywebapp.dao.model.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import mywebapp.dao.MySqlDaoFactory;
import mywebapp.dao.model.interfaces.PetDao;
import mywebapp.model.Pet;

@Repository("PetDaoJdbcImpl")
public class PetDaoJdbcImpl implements PetDao{
	
	static final private Logger LOG = LoggerFactory.getLogger(PetDaoJdbcImpl.class);

	public static Logger getLog() {
		return LOG;
	}

	@Override
	public List<Pet> getAllPets() throws DataAccessException {
		ArrayList<Pet> allPets = new ArrayList<Pet>();

		final String sql = "SELECT * FROM pet;";
		try {
			getLog().info("Setting up the connection....");

			Connection conn = MySqlDaoFactory.createConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Pet temp = new Pet();
				temp.setId(rs.getInt("id"));
				temp.setName(rs.getString("name"));
				temp.setOwner(rs.getString("owner"));
				temp.setSpecies(rs.getString("species"));
				temp.setSex(rs.getString("sex").substring(0,1));
				temp.setBirth(rs.getDate("birth"));
				temp.setDeath(rs.getDate("death"));
				allPets.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allPets;
	}

	@Override
	public List<Pet> getPetsById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pet> getPetsBySpecies(String species) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pet> getPetsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pet insertPet(Map<String, String> petInfo) {
		// TODO Auto-generated method stub
		return null;
	}

}
