package hajji.mohammed.backend.dtos;


import hajji.mohammed.backend.enums.OperationType;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;

@Data
@Builder
public class AccountOperationResponseDTO {
    private Long id;
    private Instant operationDate;
    private double amount;
    private String description;
    private OperationType operationType;
}
