package co.edu.usbcali.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.usbcali.bank.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
