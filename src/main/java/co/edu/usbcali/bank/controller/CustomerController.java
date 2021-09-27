package co.edu.usbcali.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.bank.dto.CustomerDTO;
import co.edu.usbcali.bank.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	public ResponseEntity<CustomerDTO> findById() {
		
		return ResponseEntity.ok().body(null);
	}
}
