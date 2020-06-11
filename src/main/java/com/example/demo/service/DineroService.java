package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ClienteDao;
import com.example.demo.entity.Cliente;

@Service
public class DineroService {
	@Autowired
	private ClienteDao clienteDao;
	
	public double dineroTotal() {
		double dinero = 0;
		for(Cliente c: clienteDao.findAll()) {
			dinero += c.getMonto();
		}
		return dinero;
	}
	
	public Cliente muchoDinero() {
		Cliente mayor = new Cliente();
		for(Cliente c: clienteDao.findAll()) {
			if(c.getMonto()>mayor.getMonto()) {
				mayor = c;
			}
		}
		return mayor;
	}
}
