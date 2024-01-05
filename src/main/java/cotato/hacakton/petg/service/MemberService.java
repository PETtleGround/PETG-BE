package cotato.hacakton.petg.service;

import cotato.hacakton.petg.domain.dto.auth.MemberInfoResponse;
import cotato.hacakton.petg.domain.entity.Member;
import cotato.hacakton.petg.exception.AppException;
import cotato.hacakton.petg.exception.ErrorCode;
import cotato.hacakton.petg.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findMemberById(Long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(()-> new AppException(ErrorCode.MEMBER_NOT_FOUND));
    }

    public MemberInfoResponse getMemberInfo(String email) {
        Member findMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.MEMBER_NOT_FOUND));

        return MemberInfoResponse.builder()
                .id(findMember.getId())
                .nickName(findMember.getNickName())
                .build();
    }
}
