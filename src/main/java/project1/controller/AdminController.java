package project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import project1.models.Regs;
import project1.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@GetMapping("/")
	public ModelAndView doLogIn(){
		
		ModelAndView andView = new ModelAndView("/admin/admin-login.html");
		return andView;
	}
	
	@GetMapping("/admin-home")
	public ModelAndView doHome(){
		ModelAndView andView = new ModelAndView("redirect:/admin/viewProduct");
		return andView;
	}
	
	@PostMapping("/admin-login")
	public ModelAndView doLogIn(Regs regs,Model model){
		
		ModelAndView andView = new ModelAndView();;
		boolean isAdmin =adminService.findByMail(regs);
		if(isAdmin)
			andView.setViewName("redirect:/admin/admin-home");
		else{
			model.addAttribute("msg", "Sorry! you have did't admin rights");
			andView.setViewName("/admin/admin-login.html");
		}
		return andView;
	}
}
