package co.edu.usbcali.bank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.usbcali.bank.domain.Customer;
import co.edu.usbcali.bank.domain.DocumentType;
import co.edu.usbcali.bank.repository.DocumentTypeRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
class CustomerServiceIT {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	DocumentTypeRepository documentTypeRepository;

	@Test
	@Order(1)
	void debeCrearUnCustomer() throws Exception {
		//Arrange
		Integer idDocumentType = 1;
		Integer idCustomer = 14505050;
		
		Customer customer = null;
		DocumentType documentType = null;
		
		Optional<DocumentType> doOptional = documentTypeRepository.findById(idDocumentType);
		documentType = doOptional.get();
		
		customer = new Customer();
		customer.setAddress("Avenida Siempre Viva 123");
		customer.setCustId(idCustomer);
		customer.setDocumentType(documentType);
		customer.setEmail("example@email.com");
		customer.setEnable("Y");
		customer.setName("Homero Sipmson");
		customer.setPhone("316 226 9809");
		customer.setToken("jh123gh223h4j234hkjsdfjk234");
		
		//Act
		customer = customerService.save(customer);
		
		//Assert
		assertNotNull(customer, "El customer es nulo, no se pudo grabar");
		
	}
	
	@Test
	@Order(2)
	void debeModificarUnCustomer() throws Exception {
		//Arrange
		Integer idCustomer = 14505050;
		Customer customer = customerService.findById(idCustomer).get();
	
		String nameExpected = "Homero Ibarra";
		customer.setName("Homero Ibarra");
		
		//Act
		customer = customerService.update(customer);
		
		//Assert
		assertNotNull(customer, "El customer es nulo, no se pudo grabar");
		assertEquals(nameExpected, customer.getName());
		
	}
	
	@Test
	@Order(3)
	void debeBorrarUnCustomer() throws Exception {
		//Arrange
		Integer idCustomer = 14505050;
		Customer customer = customerService.findById(idCustomer).get();
		Optional<Customer> customerOptional = null;
		
		//Act
		customerService.delete(customer);
		customerOptional = customerService.findById(idCustomer);
		
		//Assert
		assertFalse(customerOptional.isPresent(), "El customer no lo borró");
		
	}
	
	@Test
	@Order(4)
	void debeConsultarCustomers() {
		//Arrange
		List<Customer> customers = null;
		
		//Act
		customers = customerService.findAll();
		
		//Assert
		assertFalse(customers.isEmpty(), "No pudo consultar todos los clientes");
		
	}
	
	@Test
	@Order(5)
	void noDebeBorrarUnCustomer() throws Exception {
		//Arrange
		Integer idCustomer = 1;
		String actualMessage = null;
		String expectedMessage = "El customer que intenta eliminar, tiene cuentas asociadas";
		
		//Act
		RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
			customerService.deleteById(idCustomer);
		});
		
		actualMessage = runtimeException.getMessage();
		
		//Assert
		assertTrue(actualMessage.contains(expectedMessage));
		
	}

}
