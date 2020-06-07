package com.example.demo.dao;

import java.util.List;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Prestamo;
import com.example.demo.entity.User;

public interface PrestamoDao {

	List<Prestamo> findAll();
	Prestamo find(Long id);
	void insert(Prestamo nuevo);
	void update(Prestamo nuevo);// debatible
	void delete(Long id);
}
