package hajji.mohammed.backend.dtos;

import hajji.mohammed.backend.enums.OperationType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;


@Data @SuperBuilder @EqualsAndHashCode(callSuper = true)
public class SimpleAccountOperationRequestDTO extends AccountOperationDTO{
    private String accountId;
    private OperationType operationType;
}
