 package com.application.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.application.Enum.Role;

@Entity
@Table(name = "user_details")
public class UserDetailsInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Enumerated(EnumType.ORDINAL)
	private Role role;
	@Column(name="available_files")
	private long available_files;
	@Column(name="totatalfiles_uploaded")
	private long totalfiles_uploaded;

	

	public long getAvailable_files() {
		return available_files;
	}

	public void setAvailable_files(long available_files) {
		this.available_files = available_files;
	}

	public long getTotalfiles_uploaded() {
		return totalfiles_uploaded;
	}

	public void setTotalfiles_uploaded(long totalfiles_uploaded) {
		this.totalfiles_uploaded = totalfiles_uploaded;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
