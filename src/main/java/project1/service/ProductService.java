package project1.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import project1.models.Product;
import project1.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Value("${file.upload-dir}")
	private String filePath;

	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	public void addProduct(Product product) throws IOException {
		byte[] imageBytes = product.getImageFile().getBytes();
		Path path = Paths.get(filePath + product.getName() + ".jpg");
		Files.write(path, imageBytes);
		product.setFileName(product.getName() + ".jpg");
		productRepository.save(product);

	}

	public void deleteById(Long id) {
		productRepository.deleteById(id);

	}

	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);

	}

	public void updateProduct(Product product) throws IOException {
		Product productEntity =productRepository.findById(product.getId()).get();
		productEntity.setName(product.getName());
		productEntity.setDescription(product.getDescription());
		productEntity.setPrice(product.getPrice());
		
		if (product.getImageFile().getSize() != 0) {
			byte[] imageBytes = product.getImageFile().getBytes();
			Path path = Paths.get(filePath + product.getName() + ".jpg");
			Files.write(path, imageBytes);
			productEntity.setFileName(product.getName() + ".jpg");
		}
		productRepository.save(productEntity);

	}

}
