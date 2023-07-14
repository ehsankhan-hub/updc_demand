package com.aramco.updc.demand.product.models;

/*import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;*/

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTS")
/*
 * @Data
 * 
 * @AllArgsConstructor
 * 
 * @NoArgsConstructor
 * 
 * @ToString
 */
public class Product {
    @Id
    @GeneratedValue
    private int postId;
    private String subject;
    private String description;
    private String userName;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public ProductStatus getStatus() {
		return status;
	}
	public void setStatus(ProductStatus status) {
		this.status = status;
	}
	public Product(int postId, String subject, String description, String userName, ProductStatus status) {
		super();
		this.postId = postId;
		this.subject = subject;
		this.description = description;
		this.userName = userName;
		this.status = status;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

}
