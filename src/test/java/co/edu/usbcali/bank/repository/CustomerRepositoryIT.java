package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import co.edu.usbcali.bank.domain.Customer;
import co.edu.usbcali.bank.domain.DocumentType;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CustomerRepositoryIT {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	DocumentTypeRepository documentTypeRepository;

	@Test
	@Order(1)
	void debeCrearUnCustomer() {
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
		customer = customerRepository.save(customer);
		
		//Assert
		assertNotNull(customer, "El customer es nulo, no se pudo grabar");
		
	}
	
	@Test
	@Order(2)
	void debeModificarUnCustomer() {
		//Arrange
		Integer idCustomer = 14505050;
		Customer customer = customerRepository.findById(idCustomer).get();
	
		String nameExpected = "Homero Ibarra";
		customer.setName("Homero Ibarra");
		
		//Act
		customer = customerRepository.save(customer);
		
		//Assert
		assertNotNull(customer, "El customer es nulo, no se pudo grabar");
		assertEquals(nameExpected, customer.getName());
		
	}

}
