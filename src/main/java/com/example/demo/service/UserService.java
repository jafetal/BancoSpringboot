package com.example.demo.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.User.AuthorityDao;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityDao auth;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public void save(User user) {
	    user.setPassword(user.getPassword());
	    user.setAuthority(new HashSet<>(auth.findAll()));
	    System.out.print("\n\n\n\n Aquiiiiii \n\n\n\n" + userRepository.save(user).toString());
	    /****/
	}
}
