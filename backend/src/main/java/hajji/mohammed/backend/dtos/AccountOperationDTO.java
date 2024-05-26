package hajji.mohammed.backend.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data @SuperBuilder
public abstract class AccountOperationDTO {
    private double amount;
    private String description;
}
