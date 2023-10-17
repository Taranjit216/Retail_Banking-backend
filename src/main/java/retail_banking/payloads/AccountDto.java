package retail_banking.payloads;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class AccountDto {

	private Integer account_id;
	@NotEmpty
	private String account_number;
	
	private String account_type;
	@NotEmpty
	private BigDecimal balance;
	
	private Date updated_at; 
	
	private BigDecimal TransferAmount;
	
}
