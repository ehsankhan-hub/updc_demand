package com.aramco.updc.demand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aramco.updc.demand.models.Menus;
@Repository
public interface ConfigRepository extends JpaRepository<Menus, Long>{

}
