package users.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.factory.annotation.Configurable;


@Entity
@Table(name = "users")
@Configurable
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;
	
//	// used for the optimistic lock pattern
//	@Version
//    @Column(name="user_version")
//    private Integer version;
	
	@Column(name = "user_name")
	private String lastName;
	
	@Column(name = "user_firstname")
	private String firstName;
	
	@Column(name = "user_email")
	private String email;

	@Fetch(FetchMode.SUBSELECT)
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Role> roles;
	
	User() {
		// JPA requires that a default constructor is available
	}
	
	public User(String lastName, String firstName) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.roles = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public int getCharge() {
		int result = 0;
		/*
		for (Rental rental : rentals) {
			result += rental.getMovie().getPriceCategory().getCharge(rental.getRentalDays());
		}*/
		return result;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
		
}
