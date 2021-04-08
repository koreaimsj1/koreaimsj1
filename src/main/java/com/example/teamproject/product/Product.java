package com.example.teamproject.product;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
//	private String content;
	@Column(columnDefinition = "TEXT") // TEXT
	private String description;
	private long price;
	private String category;
	private String reDate;
	private long stock;

	@OneToMany
	@JoinColumn(name = "productId")
	private List<ProductFile> files;
}