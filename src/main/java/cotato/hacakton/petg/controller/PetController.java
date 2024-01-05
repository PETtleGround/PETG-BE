package cotato.hacakton.petg.controller;

import cotato.hacakton.petg.domain.dto.auth.AddPetRequest;
import cotato.hacakton.petg.domain.dto.auth.AddPetResponse;
import cotato.hacakton.petg.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/api/pet")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping("/add")
    public ResponseEntity<?> addPet(@RequestBody AddPetRequest request) {
        AddPetResponse addPetResponse = petService.addPet(request);
        log.info("펫 등록 컨트롤러 : {}", request.getBirthDay());
        return ResponseEntity.status(HttpStatus.CREATED).body(addPetResponse);
    }
}
