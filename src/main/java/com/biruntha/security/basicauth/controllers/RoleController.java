package com.biruntha.security.basicauth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biruntha.security.basicauth.models.Role;
import com.biruntha.security.basicauth.services.RoleService;


@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "*")
public class RoleController {

	@Autowired
	RoleService roleService;
	
	@GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
		return roleService.getAllRoles();
	}
}
