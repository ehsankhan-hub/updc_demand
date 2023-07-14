package com.aramco.updc.demand.product.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.aramco.updc.demand.product.models.Product;
import com.aramco.updc.demand.product.models.ProductStatus;
import com.aramco.updc.demand.product.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductRepository postRepository;

    @PostMapping("/create")
    public String createPost(@RequestBody Product post, Principal principal) {
        post.setStatus(ProductStatus.PENDING);
        post.setUserName(principal.getName());
        postRepository.save(post);
        return principal.getName() + " Your post published successfully , Required ADMIN/MODERATOR Action !";
    }

    @GetMapping("/approvePost/{postId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')  or hasRole('INITIATOR')")
    public String approvePost(@PathVariable int postId) {
        Product post = postRepository.findById(postId).get();
        post.setStatus(ProductStatus.APPROVED);
        postRepository.save(post);
        return "Post Approved !!";
    }

    @GetMapping("/approveAll")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public String approveAll() {
        postRepository.findAll().stream().filter(post -> post.getStatus().equals(ProductStatus.PENDING)).forEach(post -> {
            post.setStatus(ProductStatus.APPROVED);
            postRepository.save(post);
        });
        return "Approved all posts !";
    }

    @GetMapping("/removePost/{postId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')  or hasRole('INITIATOR')")
    public String removePost(@PathVariable int postId) {
        Product post = postRepository.findById(postId).get();
        post.setStatus(ProductStatus.REJECTED);
        postRepository.save(post);
        return "Post Rejected !!";
    }


    @GetMapping("/rejectAll")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public String rejectAll() {
        postRepository.findAll().stream().filter(post -> post.getStatus().equals(ProductStatus.PENDING)).forEach(post -> {
            post.setStatus(ProductStatus.REJECTED);
            postRepository.save(post);
        });
        return "Rejected all posts !";
    }

    @PostMapping("/viewAll")
    @PreAuthorize("hasRole('APPROVER') or hasRole('ADMIN')")
    public  List<Product> viewAll(){
        return postRepository.findAll().stream()
                .filter(post -> post.getStatus().equals(ProductStatus.PENDING))
                .collect(Collectors.toList());
    }
    
    @PostMapping("/viewProdcutByCreator")
    @PreAuthorize("hasRole('APPROVER') or hasRole('ADMIN') or hasRole('INITIATOR')")
    public  String viewProductByCreator(@RequestParam("postId") String postId){
    	List<Product> user=postRepository.findByUserName(postId);
    	String jsonString ="";
    	ObjectMapper objectMapper = new ObjectMapper();
    	try {
			jsonString = objectMapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("jsonString==="+jsonString);
    	System.out.println("user==="+user);
    	return jsonString;
    }
}
