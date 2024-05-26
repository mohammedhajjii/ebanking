package hajji.mohammed.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data @SuperBuilder
@AllArgsConstructor @NoArgsConstructor
public abstract class CustomerDTO {
    private String name;
    private String email;
}
