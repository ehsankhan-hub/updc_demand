package com.aramco.updc.demand.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aramco.updc.demand.models.Menus;

@Repository
public interface MenuRepositry extends JpaRepository<Menus, Long> {

	  @Query(nativeQuery = true,
	            value = "SELECT * FROM menu WHERE id IN(SELECT role_id FROM user_permission WHERE role_id=:role)")
	   // public List<Object[]> getMenusByRoleId();
	  
	  public List<Menus> getMenusByRoleId(String role);
	
}
