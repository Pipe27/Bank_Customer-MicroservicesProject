package co.edu.usbcali.bank.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import co.edu.usbcali.bank.domain.Account;
import co.edu.usbcali.bank.domain.Customer;
import co.edu.usbcali.bank.domain.DocumentType;
import co.edu.usbcali.bank.domain.RegisteredAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
	
	@NotNull
	private Integer custId;

	@NotNull
	@Size(min = 3, max = 100)
	private String address;

	@NotNull
	@Email
	private String email;

	@NotNull
	@Size(min = 1, max = 1)
	private String enable;

	@NotNull
	@Size(min = 4, max = 100)
	private String name;

	@NotNull
	@Size(min = 7, max = 15)
	private String phone;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String token;
	
	@NotNull
	private Integer dotyId;
	
}
