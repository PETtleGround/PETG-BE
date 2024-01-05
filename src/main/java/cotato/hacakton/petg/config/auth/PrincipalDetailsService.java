package cotato.hacakton.petg.config.auth;

import cotato.hacakton.petg.domain.entity.Member;
import cotato.hacakton.petg.exception.AppException;
import cotato.hacakton.petg.exception.ErrorCode;
import cotato.hacakton.petg.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member findMember = memberRepository.findByEmail(email)
                .orElseThrow(()->new AppException(ErrorCode.MEMBER_NOT_FOUND));
        log.info("해당 유저의 로그인 요청: {}", findMember.getEmail());
        return new PrincipalDetails(findMember);
    }
}
