package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.dao.ClienteDao;
import com.example.demo.dao.PrestamoDao;
import com.example.demo.dao.User.AuthorityDao;
import com.example.demo.dao.User.UserDao;
import com.example.demo.entity.Authority;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Prestamo;
import com.example.demo.entity.User;
import com.example.demo.service.PrestamosService;
import com.example.demo.service.UserDetailsServiceImpl;
import com.example.demo.service.UserService;

@Controller
@RequestMapping(path = "admin/cliente")
@SessionAttributes("cliente")
public class ClienteDBController {
	@Autowired
	private ClienteDao clienteDao;
	@Autowired
	UserService UserS;
	@Autowired
	AuthorityDao authDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PrestamosService pS;
	@Autowired
	PrestamoDao pDao;
	
	@GetMapping({"/listaClientes"})
	public String mostrarClientes(Model model) {
		model.addAttribute("clientes", clienteDao.findAll());
		return "admin/busquedas/ListaClientes";
	}

	@GetMapping({ "/form" })
	public String form(Model model) {
		Cliente nuevo = new Cliente();
		model.addAttribute("cliente", nuevo);
		//System.out.print("\n\n\n\n"+clienteDao.find(1l).getId_usuario().getUsername());
		return "admin/clientes/addForm";
	}

	@GetMapping({ "/form/{id}" })
	public String editar(@PathVariable Long id, Model model) {
		Cliente editar = clienteDao.find(id);
		model.addAttribute("cliente", editar);
		return "catalogo/alumno/form";
	}

	@PostMapping({ "/guardar" })
	public String guardar(Cliente cliente, BindingResult result, Model model) {
		for (Authority s : userDao.find(2l).getAuthority()) {
			System.out.print("\n\n\n\n aaaay"+ s.getAuthority() + "\n" + s.getId() + "\n\n\n\n");
		}
		if (result.hasErrors()) {
			return  "admin/clientes/addForm";
		}
		if (cliente.getId() != null && cliente.getId() > 0) {
			clienteDao.update(cliente);
		} else {
			System.out.print("\n\n\n\n Aquiiiiii \n\n\n\n");
			//User acceso = new User();
			//acceso.setUsername(cliente.getNombre());
			//acceso.setPassword("$2a$04$XJUMQEYpIDM8eiGHfl.DguvuyECHuXyGyZdZAVstKHtUCH4Y.YE0K");
			//acceso.setEnabled(true);
			//acceso.setAuthority(userDao.find(2l).getAuthority());
			//userDao.insert(acceso);
			cliente.setId_usuario(2l);//Se puso por defecto el usuario 2 en lo que se solucionan los usuarios
			clienteDao.insert(cliente);		
		}

		//sesion.setComplete();
		return "redirect:/admin/clientes";
	}

	@GetMapping({ "/form/eliminar" })
	public String forme(Model model) {
		model.addAttribute("clientes", clienteDao.findAll());
		return "admin/clientes/deleteForm";
	}
	
	@GetMapping({ "/eliminar/{id}" })
	public String eliminar(@PathVariable Long id, Model model) {
		if (id != null && id > 0) {
			pS.borrarPrestamosC(id);
			clienteDao.delete(id);
		}
		return "redirect:/admin/clientes";
	}
}
