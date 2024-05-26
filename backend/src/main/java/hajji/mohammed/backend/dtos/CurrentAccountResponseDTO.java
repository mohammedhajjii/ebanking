package hajji.mohammed.backend.dtos;

import hajji.mohammed.backend.enums.AccountStatus;
import hajji.mohammed.backend.enums.Currency;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.Instant;


@Data @SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CurrentAccountResponseDTO extends BankAccountResponseDTO{
    private double overDraft;
}
