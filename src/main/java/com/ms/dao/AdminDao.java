package com.ms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ms.model.Admin;

import jakarta.transaction.Transactional;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer>{
	Optional<Admin> findByUsername(String username);
	
	@Modifying
	@Transactional
	@Query(value = "update ADMIN set username=:newusername, password=:newpassword where username=:oldusername", nativeQuery = true)
	public int updateCredentials(
								@Param("newusername") String newusername, 
								@Param("newpassword") String newpassword, 
								@Param("oldusername") String oldusername
								);
}
