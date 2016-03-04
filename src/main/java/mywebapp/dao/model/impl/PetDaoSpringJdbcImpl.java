package mywebapp.dao.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import mywebapp.dao.model.interfaces.PetDao;
import mywebapp.model.Pet;

@Repository("PetDaoSpringJdbcImpl")
public class PetDaoSpringJdbcImpl implements PetDao{
	
	static final private Logger LOG = LoggerFactory.getLogger(PetDaoJdbcImpl.class);

	public static Logger getLog() {
		return LOG;
	}

	
	private class PetMapper implements RowMapper<Pet> {

		@Override
		public Pet mapRow(ResultSet rs, int arg1) throws SQLException {
			Pet pet = new Pet();
			
			pet.setId(rs.getInt("id"));
			pet.setName(rs.getString("name"));
			pet.setOwner(rs.getString("owner"));
			pet.setSpecies(rs.getString("species"));
			pet.setSex(rs.getString("sex"));
			pet.setBirth(rs.getDate("birth"));
			pet.setDeath(rs.getDate("death"));
			
			return pet;
		}
		
	}

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	@Qualifier("getDataSource")
	public void setJdbcTemplate(final DataSource dataScource) {
		this.jdbcTemplate = new JdbcTemplate(dataScource);
	}

	@Override
	public List<Pet> getAllPets() throws DataAccessException {
		getLog().info("Getting all Pets.....");
		List<Pet> pets = this.jdbcTemplate.query("select * from pet", new BeanPropertyRowMapper<Pet>(Pet.class));
		return pets;
	}

	@Override
	public List<Pet> getPetsById(String id) {
		List<Pet> pets = this.jdbcTemplate.query("select * from pet where id=?", new String[]{id}, new PetMapper());
		return null;
	}

	@Override
	public List<Pet> getPetsBySpecies(String species) {
		List<Pet> pets = this.jdbcTemplate.query("select * from pet where species=?", new String[]{species},new BeanPropertyRowMapper<Pet>(Pet.class));
		return pets;
	}

	@Override
	public List<Pet> getPetsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pet insertPet(Map<String, String> petInfo) {
		return null;
	}

}
