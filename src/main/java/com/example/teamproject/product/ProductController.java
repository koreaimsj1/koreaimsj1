package com.example.teamproject.product;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	private ProductRepository productRepo;
	private ProductFileRepository productFileRepo;
	private final Path FILE_PATH = Paths.get("product_file");

	public ProductController(ProductRepository productRepo, ProductFileRepository productFileRepo) {
		this.productRepo = productRepo;
		this.productFileRepo = productFileRepo;
	}

	// 목록조회
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public List<Product> getProducts(HttpServletRequest req) {
		List<Product> list = productRepo.findAll(Sort.by("id").descending());
		for (Product product : list) {
			for (ProductFile file : product.getFiles()) {
				file.setDataUrl("/product-files/" + file.getId());
			}
		}

		return list;
	}

	// 1건 추가
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public Product addProduct(@RequestBody Product product) {
		productRepo.save(product);
		return product;
	}

//	// 1건 조회
//	// @GetMapping(value="/products/{id}")
//	// feed 1건 조회
//	// GET /feeds/{id}
//	// return 객체는 Feed 1개
//	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
//	public @ResponseBody Product getProduct(@PathVariable("id") long id, HttpServletResponse res) {
//		if (productRepo.findById(id).orElse(null) == null) {
//			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
//		}
//		return null;
//	}
	@GetMapping(value = "/products/{id}")
	public Product getProductsById(@PathVariable("id") long id, HttpServletResponse res) {
		if (productRepo.findById(id).orElse(null) == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		Product products = productRepo.findById(id).orElse(null);

		return products;
	}

}