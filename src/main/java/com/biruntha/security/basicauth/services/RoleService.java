package com.biruntha.security.basicauth.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.biruntha.security.basicauth.models.Role;
import com.biruntha.security.basicauth.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;

	public ResponseEntity<List<Role>> getAllRoles() {
		try {
			List<Role> roles = roleRepository.findAll();
		    return new ResponseEntity<>(roles, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
