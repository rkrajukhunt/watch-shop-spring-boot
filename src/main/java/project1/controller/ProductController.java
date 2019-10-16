package project1.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import project1.models.Product;
import project1.service.ProductService;

@Controller
@RequestMapping("/admin")
public class ProductController {

	@Value("${file.upload-dir}")
	private String filePath;
	@Autowired
	ProductService productService;

	@GetMapping("/insertProduct")
	public ModelAndView insertProductPage() {
		ModelAndView andView = new ModelAndView("/admin/insert-product.html");
		andView.addObject("list", productService.getAllProduct());
		return andView;
	}

	@GetMapping("/viewProduct")
	public ModelAndView viewProductPage() {
		ModelAndView andView = new ModelAndView("/admin/view-product.html");
		andView.addObject("list", productService.getAllProduct());
		return andView;
	}

	@GetMapping("/updateProduct/{id}")
	public ModelAndView updateProduct(@PathVariable Long id) {
		ModelAndView andView = new ModelAndView("/admin/update-product.html");
		andView.addObject("product", productService.findById(id).get());
		return andView;
	}
	
	@PostMapping("/insert-product")
	public ModelAndView addProduct(Product product) throws IOException {
		ModelAndView andView = new ModelAndView("redirect:/admin/viewProduct");
		productService.addProduct(product);
		return andView;
	}
	
	
	@PostMapping("/update-product")
	public ModelAndView udpateProduct(Product product) throws IOException {
		ModelAndView andView = new ModelAndView("redirect:/admin/viewProduct");
		productService.updateProduct(product);
		return andView;
	}

	

	@GetMapping("/deleteProduct/{id}")
	public ModelAndView deleteProduct(@PathVariable Long id) {
		ModelAndView andView = new ModelAndView("redirect:/admin/viewProduct");
		productService.deleteById(id);
		return andView;
	}

	@ResponseBody
	@GetMapping("/get-image/{imageName}")
	public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {

		File serverFile = new File(filePath + imageName);

		return Files.readAllBytes(serverFile.toPath());
	}

}
