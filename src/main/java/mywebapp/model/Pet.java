package mywebapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Pet")
public class Pet {

	private int id;

	private String name;

	private String owner;

	private String species;

	private String sex;

	private Date birth;

	private Date death;

	private Owner ownerDetail;
	
	private Set<Toy> toys;

	@Id
	@Column(name = "pet_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Date getDeath() {
		return death;
	}

	public void setDeath(Date death) {
		this.death = death;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "owner_detail")
	public Owner getOwnerDetail() {
		return ownerDetail;
	}

	public void setOwnerDetail(Owner ownerDetail) {
		this.ownerDetail = ownerDetail;
	}

	@OneToMany
	@JoinColumn(name = "toy_id")
	@JsonIgnore
	public Set<Toy> getToys() {
		return toys;
	}

	public void setToys(Set<Toy> toys) {
		this.toys = toys;
	}

}
