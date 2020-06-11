package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.ClienteDao;
import com.example.demo.dao.PrestamoDao;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Prestamo;
import com.example.demo.models.Monto;
import com.example.demo.service.DineroService;

@Controller
@RequestMapping(path = "/cliente")
public class ClienteController {
	@Autowired
	private ClienteDao clienteDao;
	@Autowired
	private DineroService dineroService;
	@Autowired
	private PrestamoDao prestamoDao;

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
	
	@GetMapping({ "/abonar" })
	public String abono(Model model) {
		Monto dinero = new Monto();
		model.addAttribute("tipo",1);//tipo 1 abonar -1 retirar
		model.addAttribute("monto",dinero);
		return "cliente/abonar";
	}
	@GetMapping({ "/retirar" })
	public String retiro(Model model) {
		Monto dinero = new Monto();
		model.addAttribute("tipo",-1);//tipo 1 abonar -1 retirar
		model.addAttribute("monto",dinero);
		return "cliente/abonar";
	}
	
	@PostMapping({ "/abonarAction/{tipo}" })
	public String abonar(@PathVariable int tipo,Monto monto,Model model) {
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
	    
	    if(tipo==-1) {
	    	dineroService.retirar(monto.getNumero(), b.getId());
	    }else {
	    	dineroService.abonar(monto.getNumero(), b.getId());
	    }
	    
		return "cliente";
	}
	
	@GetMapping({"/prestamo"})
	public String prestamos(Model model) {
		return "cliente/controlP";
	}
	
	@GetMapping({"/myPrestamo"})
	public String myprestamos(Model model) {
		
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
		
		List<Prestamo> mios = new ArrayList<Prestamo>();
		for(Prestamo temp: prestamoDao.findAll()){
			if(temp.getCliente().getId()==b.getId()) {
				mios.add(temp);
			}
		}
		model.addAttribute("prestamos",mios);
		return "cliente/MisPrestamos";
	}
	
	@GetMapping({"/aPrestamo"})
	public String aprestamo(Model model) {
		Monto dinero = new Monto();
		model.addAttribute("monto",dinero);
		return "cliente/abonarPres";
	}
	
	@PostMapping({ "/abonarPAction" }) //lamento el el amontonamiento, pero funciona
	public String abonar(Monto monto,BindingResult result, Model model) {
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
		ArrayList<Prestamo> p = new ArrayList<Prestamo>(prestamoDao.findAll());
		p.sort((o1, o2) -> Double.compare(o2.getMonto(), o2.getMonto()));
		Prestamo editar = new Prestamo();
		for(Prestamo a: p) {
			if(a.getCliente().getId()==b.getId()) {
				editar = a;
			}
		}
		if(editar.getId()!=null) {
			if(editar.getMonto()<=monto.getNumero()) {
				Cliente actualizacion = clienteDao.find(b.getId());
				actualizacion.setMonto(actualizacion.getMonto()-editar.getMonto());
				editar.setMonto(0d);
				editar.setActivo(false);
				prestamoDao.update(editar);
				clienteDao.update(actualizacion);
			}else {
				editar.setMonto(editar.getMonto()- monto.getNumero());
				prestamoDao.update(editar);
				Cliente actualizacion = clienteDao.find(b.getId());
				actualizacion.setMonto(actualizacion.getMonto()-monto.getNumero());
				clienteDao.update(actualizacion);
			}
		}
		return "redirect:/cliente/prestamo";
	}
}