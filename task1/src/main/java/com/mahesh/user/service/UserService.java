package com.mahesh.user.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahesh.user.bean.User;
import com.mahesh.user.bean.UserFilterRequestDTO;
import com.mahesh.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	@Autowired
	private EntityManager entityManager;

	public User saveUser(User user) {
		return repository.save(user);

	}

	public List<User> saveUsers(List<User> users) {
		// TODO Auto-generated method stub
		return repository.saveAll(users);
	}

	public List<User> getUsers(List<User> users) {
		return repository.findAll();
	}

	public List<User> filterUserData(UserFilterRequestDTO userRequest) {
		return repository.findByNameAndSurnameAndPincode(userRequest.getName(), userRequest.getSurname(),
				userRequest.getPincode());
	}

	public User existingUser(User user) {
		User existingUser = repository.findById(user.getId()).orElseGet(null);
		existingUser.setId(user.getId());
		existingUser.setName(user.getName());
		existingUser.setSurname(user.getSurname());
		existingUser.setDob(user.getDob());
		existingUser.setJoiningDate(user.getJoiningDate());
		existingUser.setPincode(user.getPincode());
		return repository.save(existingUser);
	}

	public String deleteUser(int id) {
		repository.deleteById(id);
		return "user deleted" + id;

	}

	public void removeUser(int id) {
		repository.deleteById(id);
	}

	public Iterable<User> FindAll(boolean isDeleted) {
		Session session = entityManager.unwrap(Session.class);
		Filter filter = session.enableFilter("deletedUserFilter");
		filter.setParameter("isDeleted", isDeleted);
		Iterable<User> users = repository.findAll();
		session.disableFilter("deletedUserFilter");
		return users;

	}
}