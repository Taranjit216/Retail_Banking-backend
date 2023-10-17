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
public class FdDto {

	private Integer fd_id;
	
	private String account_type;
	@NotEmpty
	private BigDecimal fd_amount; 
	@NotEmpty
	private Double Interest_Rate;
	
	private BigDecimal maturity_amount; 
	
	private Date updated_at;
	
}
