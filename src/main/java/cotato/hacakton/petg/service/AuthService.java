package cotato.hacakton.petg.service;

import cotato.hacakton.petg.domain.dto.auth.JoinRequest;
import cotato.hacakton.petg.domain.entity.Member;
import cotato.hacakton.petg.exception.AppException;
import cotato.hacakton.petg.exception.ErrorCode;
import cotato.hacakton.petg.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void createMember(JoinRequest request) {

        validateDuplicateEmail(request.getEmail());
        validateDuplicateNickName(request.getNickName());
        log.info("회원 가입 서비스, 검증 완료 : {}", request.getEmail());
        Member createdMember = Member.builder()
                .email(request.getEmail())
                .nickName(request.getNickName())
                .password(encoder.encode(request.getPassword()))
                .build();
        memberRepository.save(createdMember);
    }

    private void validateDuplicateEmail(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new AppException(ErrorCode.EMAIL_DUPLICATED);
        }
    }

    private void validateDuplicateNickName(String nickName) {
        if (memberRepository.findByNickName(nickName).isPresent()) {
            throw new AppException(ErrorCode.NICKNAME_DUPLICATE);
        }
    }
}
