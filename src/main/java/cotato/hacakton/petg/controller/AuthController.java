package cotato.hacakton.petg.controller;

import cotato.hacakton.petg.domain.dto.auth.JoinRequest;
import cotato.hacakton.petg.service.AuthService;
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
@RequestMapping("/v1/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/join")
    public ResponseEntity<?> createMember(@RequestBody JoinRequest request){
        authService.createMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입 성공");
    }
}
