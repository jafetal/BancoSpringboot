package com.example.demo.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/admin")
public class AdminController {
	@GetMapping({"/"})
	public String index(Model model) {
		return "admin";
	}
}