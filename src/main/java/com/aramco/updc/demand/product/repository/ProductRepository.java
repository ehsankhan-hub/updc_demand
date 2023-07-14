package com.aramco.updc.demand.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aramco.updc.demand.product.models.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
	
	List<Product> findByUserName(String name);
		
	
}
