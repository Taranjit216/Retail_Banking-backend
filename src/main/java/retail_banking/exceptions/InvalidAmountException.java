package retail_banking.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvalidAmountException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidAmountException() {
		super(String.format("Account does't have this much amount"));
	}
}
