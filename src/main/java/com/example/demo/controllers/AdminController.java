package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.ClienteDao;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {
	@Autowired
	private ClienteDao clienteDao;
	
	@GetMapping({ "/", "" })
	public String admin(Model model) {
		return "admin";
	}
	
	@GetMapping({"/clientes"})
	public String clientes(Model model) {
		model.addAttribute("clientes", clienteDao.findAll());
		return "admin/clientes/controlClientes";
	}
	@GetMapping({"/prestamos"})
	public String prestamos(Model model) {
		return "admin/prestamos/controlPrestamos";
	}
	
}