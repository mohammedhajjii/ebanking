package hajji.mohammed.backend.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;


@Data @SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class SavingAccountResponseDTO extends BankAccountResponseDTO{
    private double interestRate;
}
