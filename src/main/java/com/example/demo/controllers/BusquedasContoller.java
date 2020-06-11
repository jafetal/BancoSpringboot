package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.ClienteDao;
import com.example.demo.dao.PrestamoDao;
import com.example.demo.entity.Prestamo;
import com.example.demo.service.DineroService;

@Controller
@RequestMapping(path = "/busqueda")
public class BusquedasContoller {
	@Autowired
	private ClienteDao clienteDao;
	@Autowired
	private PrestamoDao prestamoDao;
	@Autowired
	private DineroService dineroService;

	@GetMapping({"/Clientes"})
	public String mostrarClientes(Model model) {
		model.addAttribute("clientes", clienteDao.findAll());
		return "admin/busquedas/ListaClientes";
	}
	
	@GetMapping({"/Prestamos"})
	public String mostrarPrestamos(Model model) {
		model.addAttribute("filtro", "todos");
		model.addAttribute("prestamos", prestamoDao.findAll());
		return "admin/busquedas/ListaPrestamos";
	}
	
	@GetMapping({"/Dinero"})
	public String mostrarDinero(Model model) {
		model.addAttribute("dinero",dineroService.dineroTotal());
		model.addAttribute("cliente", dineroService.muchoDinero());
		return "admin/busquedas/Dinero";
	}
	
	@GetMapping({"/Prestamos/pagados"})
	public String mostrarPrestamosp(Model model) {
		model.addAttribute("filtro", "pagados");
		List<Prestamo> temporal = new ArrayList<Prestamo>();
		for(Prestamo a:prestamoDao.findAll()) {
			if(!a.isActivo()) { 
				temporal.add(a);
			}
		}
		model.addAttribute("prestamos", temporal);
		return "admin/busquedas/ListaPrestamos";
	}
	@GetMapping({"/Prestamos/pendientes"})
	public String mostrarPrestamosNop(Model model) {
		model.addAttribute("filtro", "pendientes");
		List<Prestamo> temporal = new ArrayList<Prestamo>();
		for(Prestamo a:prestamoDao.findAll()) {
			if(a.isActivo()) {
				temporal.add(a);
			}
		}
		model.addAttribute("prestamos", temporal);
		return "admin/busquedas/ListaPrestamos";
	}
}
