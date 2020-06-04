package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	
	@GetMapping({"/","/login"})
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/menu")
	public String menu(Model model) {
		return "menu";
	}
	
	@GetMapping("/cliente")
	public String user(Model model) {
		return "cliente";
	}
	
	@GetMapping("/admin")
	public String admin(Model model) {
		return "admin";
	}
}
