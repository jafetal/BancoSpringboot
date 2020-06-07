package com.example.demo.dao.User;

import java.util.List;

import com.example.demo.entity.User;

public interface UserDao {
	List<User> findAll();
	User find(Long id);
	void insert(User nuevo);
	void update(User nuevo);
	void delete(Long id);
}
