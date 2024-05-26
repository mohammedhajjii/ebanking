package hajji.mohammed.backend.dtos;

import hajji.mohammed.backend.enums.Currency;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;


@Data @SuperBuilder @EqualsAndHashCode(callSuper = true)
public class SavingAccountRequestDTO extends BankAccountRequestDTO{
    private double interestRate;
}
