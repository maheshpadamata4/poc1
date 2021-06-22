package com.mahesh.user.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mahesh.user.bean.User;
import com.mahesh.user.bean.UserFilterRequestDTO;
import com.mahesh.user.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService service;
	
	@PostMapping("/addUser")
	public User addUser(@Valid  @RequestBody User user) {
		return service.saveUser(user);
		
	}
	@PostMapping("/addUsers")
	public List<User> addUsers(@RequestBody List<User> users) {
		
		List<User> userList = new ArrayList<>();
//		return userList.stream().filter(x -> x.getName().equalsIgnoreCase("mahesh"))
//			//	&& x.getSurname().equalsIgnoreCase("padamata") && x.getDob().compareTo()==0).sorted()
//				.collect(Collectors.toList());
		return service.saveUsers(users);
	}
	
	@GetMapping("/filterUser")
	public List<User> addUser(@RequestBody UserFilterRequestDTO user) {
		
		return service.filterUserData(user);
		
	}
	
	@PostMapping("/update")
	public User updateUser(@RequestBody User user) {
		return service.saveUser(user);
		
	}
	@DeleteMapping("/delete/{id}")
		public String deleUser(@PathVariable int id) {
		return service.deleteUser(id);
	}
	@DeleteMapping("remove/{id}")
	public  void removeUser(@PathVariable int id) {
		service.removeUser(id);
	}
	@GetMapping("/findAll")
    public Iterable<User> findAll(@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted) {
        return service.FindAll(isDeleted);
}
}
