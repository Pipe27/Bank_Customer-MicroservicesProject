package co.edu.usbcali.bank.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.usbcali.bank.domain.Customer;
import co.edu.usbcali.bank.dto.CountDTO;
import co.edu.usbcali.bank.dto.CustomerDTO;
import co.edu.usbcali.bank.mapper.CustomerMapper;
import co.edu.usbcali.bank.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/customer")
@Slf4j
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerMapper customerMapper;
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> findById(@PathVariable("id") Integer id) throws Exception {
		log.info("Request to findById with customer id: " + id);
		Customer customer = (customerService.findById(id).isPresent() == true) ? customerService.findById(id).get() : null;
		CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
		return ResponseEntity.ok().body(customerDTO);
	}
	
	@GetMapping()
	public ResponseEntity<List<CustomerDTO>> findAll() throws Exception {
		List<CustomerDTO> customerDTOs = customerMapper.customerListToCustomerDTOList(customerService.findAll());
		return ResponseEntity.ok().body(customerDTOs);
	}
	
	@PostMapping
	public ResponseEntity<CustomerDTO> save(@Valid @RequestBody CustomerDTO customerDTO) throws Exception {
		Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
		customer = customerService.save(customer);
		customerDTO = customerMapper.customerToCustomerDTO(customer);
		return ResponseEntity.ok().body(customerDTO);
	}
	
	@PutMapping
	public ResponseEntity<CustomerDTO> update(@Valid @RequestBody CustomerDTO customerDTO) throws Exception {
		Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
		customer = customerService.update(customer);
		customerDTO = customerMapper.customerToCustomerDTO(customer);
		return ResponseEntity.ok().body(customerDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomerDTO> delete(@PathVariable("id") Integer id) throws Exception {
		log.info("Request to findById with customer id: " + id);
		customerService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/count")
	public ResponseEntity<CountDTO> count() throws Exception {
		return ResponseEntity.ok(new CountDTO(customerService.count()));
	}
	
}
