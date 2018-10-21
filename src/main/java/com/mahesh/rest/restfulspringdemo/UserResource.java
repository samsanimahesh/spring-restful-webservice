package com.mahesh.rest.restfulspringdemo;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.aspectj.weaver.AjAttribute.MethodDeclarationLineNumberAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mahesh.rest.restfulspringdemo.exceptions.UserNotFoundException;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService userService;
	
	@GetMapping(path="/users")
	public List<User> findAll(){
		return userService.findAll();
	}
	
	@GetMapping(path="/users/{id}")
	public Resource<User> findUserById(@PathVariable int id){
		User user = userService.findUser(id);
		if(user == null)
			throw new UserNotFoundException("User not found");
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAll());
		resource.add(linkTo.withRel("Link to all users"));
		return resource;
		
	}
	
	@PostMapping(path="/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User createdUser = userService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping(path="/users/{id}")
	public void deleteUserById(@PathVariable int id){
		User user = userService.deleteUserById(id);
		if(user == null){
			throw new UserNotFoundException("User not found");
		}
	}

}
