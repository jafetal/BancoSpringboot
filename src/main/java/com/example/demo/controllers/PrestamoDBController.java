package com.example.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import java.sql.Date;
import java.time.LocalDateTime;  

import com.example.demo.dao.ClienteDao;
import com.example.demo.dao.PrestamoDao;
import com.example.demo.dao.User.AuthorityDao;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Prestamo;
import com.example.demo.models.Monto;
import com.example.demo.service.fechaService;

@Controller
@RequestMapping(path = "admin/prestamo")
@SessionAttributes("prestamo")
public class PrestamoDBController {
	@Autowired
	private PrestamoDao prestamoDao;
	@Autowired
	private ClienteDao clienteDao;
	@Autowired
	private AuthorityDao authDao;
	@Autowired
	private fechaService fecha;
	
	@GetMapping({"/form/editar"})
	public String fEdit(Model model) {
		model.addAttribute("prestamos", prestamoDao.findAll());
		return "admin/prestamos/editForm";
	}

	@GetMapping({ "/form/{id}" })
	public String form(@PathVariable Long id, Model model) {
		Prestamo cliente = new Prestamo();
		cliente.setCliente(clienteDao.find(id));
		cliente.setFechaCreacion(fecha.getCurrentTimeUsingDate());
		cliente.setActivo(true);
		model.addAttribute("cliente", clienteDao.find(id));
		model.addAttribute("prestamo", cliente);
		//System.out.print("\n\n\n\n"+PrestamoDao.find(1l).getId_usuario().getUsername());
		return "admin/prestamos/prestamoForm";
	}
	
	@GetMapping({ "/selectC" })
	public String seleccionaCliente(Model model) {
		model.addAttribute("clientes", clienteDao.findAll());
		//System.out.print("\n\n\n\n"+PrestamoDao.find(1l).getId_usuario().getUsername());
		return "admin/prestamos/SelectClient";
	}
	
	@GetMapping({ "/abono" })
	public String seleccionaCliente2(Model model) {
		Monto a = new Monto();
		model.addAttribute("Monto", a);
		model.addAttribute("error",false);
		model.addAttribute("error2",false);
		model.addAttribute("clientes", clienteDao.findAll());
		//System.out.print("\n\n\n\n"+PrestamoDao.find(1l).getId_usuario().getUsername());
		return "admin/prestamos/SelectAbonar";
	}

	@GetMapping({ "/abonar/{id}" }) //se abona al prestamo que más debe
	public String abonar(@PathVariable Long id,@Valid Monto abon,BindingResult result, Model model) {
		ArrayList<Prestamo> p = new ArrayList<Prestamo>(prestamoDao.findAll());
		p.sort((o1, o2) -> Double.compare(o2.getMonto(), o2.getMonto()));
		Prestamo editar = new Prestamo();
		for(Prestamo a: p) {
			if(a.getCliente().getId()==id) {
				editar = a;
			}
		}
		if(result.hasErrors()) {
			Monto a = new Monto();
			model.addAttribute("Monto", a);
			model.addAttribute("clientes", clienteDao.findAll());
			model.addAttribute("error2",true);
			model.addAttribute("error",false);
			return "admin/prestamos/SelectAbonar";
		}else {
			if(editar.getId()!=null) {
				editar.setMonto(editar.getMonto()- abon.getNumero());
				prestamoDao.update(editar);
				Cliente actualizacion = clienteDao.find(id);
				actualizacion.setMonto(actualizacion.getMonto()-abon.getNumero());
				clienteDao.update(actualizacion);
			}else {
				Monto a = new Monto();
				model.addAttribute("Monto", a);
				model.addAttribute("error2",false);
				model.addAttribute("clientes", clienteDao.findAll());
				model.addAttribute("error",true);
				return "admin/prestamos/SelectAbonar";
			}
		}
		return "redirect:/admin/prestamos";
	}
	
	
	@GetMapping({ "/editar/{id}" })
	public String editar(@PathVariable Long id, Model model) {
		ArrayList<Prestamo> p = new ArrayList<Prestamo>(prestamoDao.findAll());
		p.sort((o1, o2) -> Double.compare(o2.getMonto(), o1.getMonto()));
		Prestamo editar = prestamoDao.find(id);
		model.addAttribute("prestamo", editar);
		return "admin/prestamos/prestamoForm";
	}

	
	@GetMapping({ "/mostrar" })
	public String ver(Model model) {
		ArrayList<Prestamo> p = new ArrayList<Prestamo>(prestamoDao.findAll());
		p.sort((o1, o2) -> Double.compare(o2.getMonto(), o1.getMonto()));
		
		model.addAttribute("prestamos", p);
		return "admin/prestamos/ListaPrestamos";
	}

	@PostMapping({ "/guardar" })
	public String guardar(Prestamo prestamo, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute(clienteDao.find(prestamo.getCliente().getId()));
			return "admin/prestamos/prestamoForm";
		}if (prestamo.getId() != null && prestamo.getId() > 0) {
			prestamoDao.update(prestamo);
		} else {
			Cliente cliente = prestamo.getCliente();
			cliente.setMonto(cliente.getMonto()+prestamo.getMonto());
			clienteDao.update(cliente);
			System.out.print("\n\n\n\nTratando de agregar prestamo al usaurio: " + prestamo.getCliente().getNombre() + "id: " + prestamo.getId());
			switch(prestamo.getTipo().toString()) {
			case "1":
				prestamo.setMonto(prestamo.getMonto() + (prestamo.getMonto()*0.05)); //Se le suma el 3% a la deuda
				break;
			case "2":
				prestamo.setMonto(prestamo.getMonto() + (prestamo.getMonto()*0.10)); //Se le suma el 10% a la deuda
				break;
			case "3":
				prestamo.setMonto(prestamo.getMonto() + (prestamo.getMonto()*0.30)); //Se le suma el 30% a la deuda
				break;
			}
			prestamoDao.insert(prestamo);
		}
		return "redirect:/admin/prestamos";
	}
}
