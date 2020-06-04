package com.example.demo.dao;

import java.util.List;
import com.example.demo.entity.Cliente;

public interface ClienteDao {

	List<Cliente> findAll();
	Cliente find(Long id);
	void insert(Cliente nuevo);
	void update(Cliente nuevo);// debatible
	void delete(Long id);
}
