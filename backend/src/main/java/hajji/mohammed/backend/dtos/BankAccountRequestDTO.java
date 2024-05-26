package hajji.mohammed.backend.dtos;

import hajji.mohammed.backend.enums.Currency;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data @SuperBuilder
public abstract class BankAccountRequestDTO {
    private Long customerId;
    private double initialBalance;
    private Currency currency;
}
