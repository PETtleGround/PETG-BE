package cotato.hacakton.petg.controller;

import cotato.hacakton.petg.config.jwt.JwtUtil;
import cotato.hacakton.petg.domain.dto.auth.MemberInfoResponse;
import cotato.hacakton.petg.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @GetMapping("/info")
    public ResponseEntity<?> getMemberInfo(@RequestHeader("Authorization") String authorizationHeader){
        String accessToken = jwtUtil.getBearer(authorizationHeader);
        String email = jwtUtil.getEmail(accessToken);
        MemberInfoResponse memberInfo = memberService.getMemberInfo(email);
        return ResponseEntity.ok().body(memberInfo);
    }
}
