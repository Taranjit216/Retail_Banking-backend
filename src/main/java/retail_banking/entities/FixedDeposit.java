package retail_banking.entities;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class FixedDeposit {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer fd_id;
	
//	@Column(length = 9)
//	private String account_number;
	
	private String account_type;
	
	private BigDecimal fd_amount; 
	
	private Double Interest_Rate;
	
	private BigDecimal maturity_amount; 
	
	private Date updated_at;
	
	private Integer tenor;
	
	//private Date maturity_Date;
	
	@ManyToOne
	//@JoinColumn(name = "account_number")
	private Account account;
	
//	@ManyToOne
//	private User user;
	
//	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
//	private Set<Comment> comments = new HashSet<>();
	
}
