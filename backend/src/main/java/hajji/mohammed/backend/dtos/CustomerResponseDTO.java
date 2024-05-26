package hajji.mohammed.backend.dtos;


import lombok.*;
import lombok.experimental.SuperBuilder;


@Data @SuperBuilder @NoArgsConstructor @EqualsAndHashCode(callSuper = true)
public class CustomerResponseDTO extends CustomerDTO{
    private Long id;
}
