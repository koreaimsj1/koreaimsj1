package com.example.teamproject.cart;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.example.teamproject.product.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CartProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private int price;
	private int quantitiy;
	private long cartId;

	@OneToOne
	private Product product;

}
