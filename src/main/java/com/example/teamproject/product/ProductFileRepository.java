package com.example.teamproject.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductFileRepository extends JpaRepository<ProductFile, Long> {
	List<ProductFile> findById(long Id);
}