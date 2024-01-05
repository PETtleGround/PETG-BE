package cotato.hacakton.petg.domain.dto.auth;

import cotato.hacakton.petg.domain.entity.PetCategory;
import cotato.hacakton.petg.domain.entity.PetType;
import lombok.Getter;

@Getter
public class AddPetRequest {

    private String name;
    private PetCategory petCategory;
    private PetType petType;
    private Long memberId;
    private int year;
    private int month;
    private int date;
}
