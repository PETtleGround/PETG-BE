package cotato.hacakton.petg.repository;

import cotato.hacakton.petg.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
