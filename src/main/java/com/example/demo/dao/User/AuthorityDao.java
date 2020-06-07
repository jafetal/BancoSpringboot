package com.example.demo.dao.User;

import java.util.List;

import com.example.demo.entity.Authority;

public interface AuthorityDao {
	List<Authority> findAll();
	Authority find(Long id);
}