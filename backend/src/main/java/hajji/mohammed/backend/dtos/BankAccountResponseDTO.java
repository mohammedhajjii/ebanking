package hajji.mohammed.backend.dtos;

import hajji.mohammed.backend.enums.AccountStatus;
import hajji.mohammed.backend.enums.Currency;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data @SuperBuilder
public abstract class BankAccountResponseDTO {
    private String id;
    private Instant createdAt;
    private double balance;
    private Currency currency;
    private AccountStatus accountStatus;
    private CustomerResponseDTO customer;
}
