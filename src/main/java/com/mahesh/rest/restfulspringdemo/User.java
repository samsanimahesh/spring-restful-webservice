package com.mahesh.rest.restfulspringdemo;

import java.util.Date;

import javax.validation.constraints.Size;

public class User {
	
	private Integer id;
	@Size(max=2, message="Name should not be more than 3 characters")
	private String name;
	private Date birthDate;
	
	public User(){
		
	}
	
	public User(Integer id, String name, Date date){
		super();
		this.id = id;
		this.name = name;
		this.birthDate = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	

}
