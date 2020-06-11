package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
	
	public void abonar(double dinero,long id) {
		Cliente temporal = clienteDao.find(id);
		temporal.setMonto(temporal.getMonto()+dinero);
		clienteDao.update(temporal);
	}
	public void retirar(double dinero,long id) {
		Cliente temporal = clienteDao.find(id);
		if(!(dinero>temporal.getMonto())) {
			temporal.setMonto(temporal.getMonto()-dinero);
			clienteDao.update(temporal);
		}else {
			System.out.print("No hay suficiente saldo para retirar.....");//no tuve tiempo de mostrarlo en pantalla
		}
		
	}
}
