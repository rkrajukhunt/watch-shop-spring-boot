package project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import project1.models.Cate;
import project1.models.Feed;
import project1.models.Regs;
import project1.repository.CateDAO;
import project1.repository.FeedDAO;
import project1.repository.RegDAO;
import project1.service.LoginService;
import project1.service.ProductService;

@Component
@Controller
public class LogController {

	@Autowired
	private RegDAO reg;
	@Autowired
	private FeedDAO feed;
	@Autowired
	private CateDAO cate;
	@Autowired
	LoginService logInService;
	@Autowired
	ProductService productService;

	@RequestMapping("/reg")
	public ModelAndView registerUser(Regs regs) {

		ModelAndView andView = new ModelAndView("redirect:/login");
		logInService.registerUser(regs);
		return andView;

	}

	@PostMapping("/loginUser")
	public ModelAndView loginUser(Regs regs, Model model) {

		ModelAndView andView = new ModelAndView();
		if (logInService.loginUser(regs)) {
			andView.setViewName("redirect:/category");
		} else {
			model.addAttribute("msg", "Username and password not matched");
			andView.setViewName("/login");
		}

		return andView;

		// return email;
	}

	@RequestMapping("/delete")
	public String hello3() {
		return "admin_rti_page.html";
	}

	@RequestMapping("/insert")
	public String hello4() {
		return "ans_rti.html";
	}

	@RequestMapping("/upd")
	public String hello5() {
		return "upd.html";
	}

	@RequestMapping("/login")
	public String hello1() {
		return "login.html";
	}

	@RequestMapping("/log")
	public String addNewEmployee(@RequestParam(value = "name") String unm,
			@RequestParam(value = "email") String e,
			@RequestParam(value = "cmd") String c,
			@RequestParam(value = "pwd") String p) {

		return "index";

	}

	@RequestMapping("/register")
	public String repage() {
		return "register";
	}

	@RequestMapping("/sendmessage")
	public String fetch1(@RequestParam(value = "name") String nm, @RequestParam(value = "email") String e,
			@RequestParam(value = "subject") String sub, @RequestParam(value = "message") String mes) {

		// String unam=String.valueOf(reg.findByUname(unm).getUname());
		// String email=String.valueOf(reg.findByEmail(unm).getPwd());
		// return unm;

		Feed f = new Feed();

		f.setName(nm);

		f.setEmail(e);

		f.setSubject(sub);

		f.setMessage(mes);

		feed.save(f);
		System.out.println("dfsfsfsdf");
		return "contact.html";

		// return email;
	}

	@RequestMapping("/ans")
	public String addproduct(@RequestParam(value = "pname") String nm, @RequestParam(value = "file") String f,
			@RequestParam(value = "price") String p
	// @RequestParam(value="pmessage") String m
	) {

		// String unam=String.valueOf(reg.findByUname(unm).getUname());
		// String email=String.valueOf(reg.findByEmail(unm).getPwd());
		// return unm;

		Cate c = new Cate();

		c.setPname(nm);

		c.setFile(f);

		c.setPrice(p);

		cate.save(c);
		// System.out.println("dfsfsfsdf");
		return "contact.html";

		// return email;
	}

	@RequestMapping("/gallery")
	public ModelAndView galpage() {

		ModelAndView andView = new ModelAndView("redirect:/viewProduct");
		return andView;
	}

	@RequestMapping("/contact")
	public String galpage1() {

		return "contact.html";
	}

	@RequestMapping("/category")
	public String catpage(Model model) {

		model.addAttribute("list", productService.getAllProduct());
		return "category.html";
		// return cate.findAll();
	}

	@RequestMapping("/checkout")
	public String chepage() {
		return "checkout";
	}

	@RequestMapping("/confirmation")
	public String confpage() {
		return "confirmation";
	}

}