package com.kuliah.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kuliah.main.entity.AdminUser;
import com.kuliah.main.services.ModelAdminUser;

@Controller
public class AdminUserPage {
	
	@Autowired
	ModelAdminUser modelAdminUser;
	
	
	@GetMapping("/adminuser/view")
	public String viewIndexAdminUser(Model model) {
		
		model.addAttribute("listAdminUser",modelAdminUser.getAllAdminUser());
		model.addAttribute("active",1);
		model.addAttribute("test","Test Aja");
		
		return "view_adminuser";
	}
	
	
	@GetMapping("/adminuser/add")
	public String viewAddAdminUser(Model model) {
		
		// buat penampung data adminuser di halaman htmlnya
		model.addAttribute("adminuser",new AdminUser());
		
		return "add_adminuser";
	}
	
	@PostMapping("/adminuser/view")
	  public String addAdminUser(@ModelAttribute AdminUser adminuser, Model model) {
		
		// buat penampung data adminuser di halaman htmlnya
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String plainPassword = adminuser.getPassword();
		String encodedPassword = passwordEncoder.encode(plainPassword);
        adminuser.setPassword(encodedPassword);		
		
		this.modelAdminUser.addAdminUser(adminuser);
		model.addAttribute("listAdminUser",modelAdminUser.getAllAdminUser());
		
		
		return "redirect:/adminuser/view";
	}
	
	
	@GetMapping("/adminuser/update/{id}")
	public String viewUpdateAdminUser(@PathVariable String id, Model model) {
		
		AdminUser adminuser = modelAdminUser.getAdminUserById(id);
		// buat penampung data adminuser di halaman htmlnya
		model.addAttribute("adminuser",adminuser);
		
		return "add_adminuser";
	}
	
	@GetMapping("/adminuser/delete/{id}")
	public String deleteAdminUser(@PathVariable String id, Model model) {
		
		this.modelAdminUser.deleteAdminUser(id);
		model.addAttribute("listAdminUser",modelAdminUser.getAllAdminUser());
		
		
		return "redirect:/adminuser/view";
	}

}
