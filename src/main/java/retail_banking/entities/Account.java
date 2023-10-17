package retail_banking.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Account {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer account_id;
	
	@Column(length = 9)
	private String account_number;
	
	private String account_type;
	
	private BigDecimal balance;
	
	private Date updated_at; 
	
	private BigDecimal TransferAmount;
	
//	@ManyToOne
//	@JoinColumn(name = "category_id")
//	private Category category;
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private Set<FixedDeposit> fixedDeposits = new HashSet<>();
	
}
