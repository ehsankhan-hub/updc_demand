package com.aramco.updc.demand.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aramco.updc.demand.models.Menus;
import com.aramco.updc.demand.payload.response.MessageResponse;
import com.aramco.updc.demand.product.models.Product;
import com.aramco.updc.demand.product.models.ProductStatus;
import com.aramco.updc.demand.product.repository.ProductRepository;
import com.aramco.updc.demand.repository.MenuRepositry;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	
	 @Autowired
	 private ProductRepository postRepository;
	 
		 
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	
	/*
	 * @GetMapping("/initiator")
	 * 
	 * @PreAuthorize("hasRole('INITIATOR') or hasRole('ADMIN')") public
	 * ResponseEntity<?> initiatorAccess() { return ResponseEntity.ok(new
	 * MessageResponse("initiator  page!")); }
	 */
	
	@PostMapping("/initiator")
    @PreAuthorize("hasRole('APPROVER') or hasRole('ADMIN') or hasRole('INITIATOR')")
    public  List<Product> viewAll(){
        return postRepository.findAll().stream()
                .filter(post -> post.getStatus().equals(ProductStatus.PENDING))
                .collect(Collectors.toList());
    }
	
	@GetMapping("/approver")
	@PreAuthorize("hasRole('APPROVER') or hasRole('ADMIN')")
	public ResponseEntity<?>  approverAccess() {
		return ResponseEntity.ok(new MessageResponse("Approver  page!"));
	}
	
	
	
	
	
	
}
