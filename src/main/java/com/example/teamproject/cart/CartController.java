package com.example.teamproject.cart;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

	private CartRepository cartRepo;

	@Autowired
	public CartController(CartRepository cartRepo) {
		this.cartRepo = cartRepo;

	}

	// 목록조회
	@RequestMapping(value = "/carts", method = RequestMethod.GET)
	public List<Cart> getCart(HttpServletRequest req) {
		return cartRepo.findAll();
	}

	@RequestMapping(value = "/carts", method = RequestMethod.POST)
	public Cart addCart(@RequestBody Cart cart) {

		cartRepo.save(cart);

		return cart;
	}

	// id로 역정렬
	@RequestMapping(value = "/cart/sort", method = RequestMethod.GET)
	public List<Cart> getCartSorted() {
		// 전체 목록 조회, id 필드로 역정렬
		return cartRepo.findAll(Sort.by("id").descending());
	}

	@RequestMapping(value = "/cart/paging", method = RequestMethod.GET)
	public List<Cart> getCartPaging(@RequestParam("page") int page, @RequestParam("size") int size) {
		// 전체 목록 조회, 페이징
		return cartRepo.findAll(PageRequest.of(page, size)).toList();
	}

	@RequestMapping(value = "/cart/paging-and-sort", method = RequestMethod.GET)
	public Page<Cart> getCartPagingAndSort(@RequestParam("page") int page, @RequestParam("size") int size) {
		// 전체 목록 조회, 페이징, id 역정렬
		return cartRepo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
	}

}