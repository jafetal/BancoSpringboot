package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.ClienteDao;
import com.example.demo.entity.Cliente;

@Controller
@RequestMapping(path = "/cliente")
public class ClienteController {
	@Autowired
	private ClienteDao clienteDao;

	@GetMapping({ "/informacion" })
	public String form(Model model) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String username = user.getUsername();
	    System.out.print(username);
	    Cliente b = new Cliente();
	    
	    //Se que es una mala implementación, pero como no esta relacionado el usuario y el cliente, no tuve opción más que usar el nombre
	    for(Cliente a: clienteDao.findAll()) {
	    	if((a.getNombre().toLowerCase()).equals(username.toLowerCase())) {
	    		System.out.print("\n\nComparando: " + a.getNombre() + " Con: " + username);
	    		b = a;
	    		break;
	    	}
	    }
	    //System.out.print("\n\n\nMandando a " + b.getNombre());
		model.addAttribute("cliente", b);
		return "cliente/Detalles";
	}
	
	@PostMapping({"/informacionUp"})
	public String cliente(Cliente cliente,BindingResult result,Model model) {
	    if (result.hasErrors()) {
			return  "cliente/Detalles";
		}
		
		return "cliente/Detalles";
	}
	
}