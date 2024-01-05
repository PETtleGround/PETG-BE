package cotato.hacakton.petg.domain.dto.auth;

import cotato.hacakton.petg.domain.entity.PetCategory;
import cotato.hacakton.petg.domain.entity.PetType;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AddPetRequest {

    private String name;
    private PetCategory petCategory;
    private PetType petType;
    private Long memberId;
    private String birthDay;
}
