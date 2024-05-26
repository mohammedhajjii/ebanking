package hajji.mohammed.backend.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class TransferOperationRequestDTO extends AccountOperationDTO{
    private String from;
    private String to;
}
