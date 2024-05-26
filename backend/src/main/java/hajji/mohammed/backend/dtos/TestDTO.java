package hajji.mohammed.backend.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class TestDTO {
    private Long id;
    private InnerDTO innerDTO = new InnerDTO();
}
