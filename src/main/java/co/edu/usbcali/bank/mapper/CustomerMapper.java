package co.edu.usbcali.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.bank.domain.Customer;
import co.edu.usbcali.bank.dto.CustomerDTO;

@Mapper
public interface CustomerMapper {

	@Mapping(source = "documentType.dotyId", target = "dotyId")
	public CustomerDTO customerToCustomerDTO(Customer customer);
	
	@Mapping(target = "documentType.dotyId", source = "dotyId")
	public Customer customerDTOToCustomer(CustomerDTO customerDTO);
	
	public List<CustomerDTO> customerListToCustomerDTOList(List<Customer> customers);
	
	public List<Customer> customerDTOListToCustomerList (List<CustomerDTO> customerDTOs);
	
}
