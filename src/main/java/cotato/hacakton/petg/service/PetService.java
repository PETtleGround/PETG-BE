package cotato.hacakton.petg.service;

import cotato.hacakton.petg.domain.dto.auth.AddPetRequest;
import cotato.hacakton.petg.domain.dto.auth.AddPetResponse;
import cotato.hacakton.petg.domain.entity.Member;
import cotato.hacakton.petg.domain.entity.Pet;
import cotato.hacakton.petg.exception.AppException;
import cotato.hacakton.petg.exception.ErrorCode;
import cotato.hacakton.petg.repository.MemberRepository;
import cotato.hacakton.petg.repository.PetRepository;
import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final MemberRepository memberRepository;

    public AddPetResponse addPet(AddPetRequest request){

        Member findMember = memberRepository.findById(request.getMemberId())
                .orElseThrow(()-> new AppException(ErrorCode.MEMBER_NOT_FOUND));

        LocalDate birth = LocalDate.of(request.getYear(), request.getMonth(), request.getDate());

        Pet createdPet = Pet.builder()
                .name(request.getName())
                .category(request.getPetCategory())
                .type(request.getPetType())
                .birthDay(birth)
                .member(findMember)
                .build();
        petRepository.save(createdPet);

        return AddPetResponse.builder()
                .id(createdPet.getId())
                .build();
    }
}
