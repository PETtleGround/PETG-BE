package cotato.hacakton.petg.domain.entity;

import static jakarta.persistence.FetchType.*;

import cotato.hacakton.petg.global.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pet extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pet_name")
    private String name;

    @Column(name = "pet_category")
    @Enumerated(EnumType.STRING)
    private PetCategory category;

    @Column(name = "pet_type")
    @Enumerated(EnumType.STRING)
    private PetType type;

    @Column(name = "pet_birth")
    private LocalDate birthday;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Pet(String name, PetCategory category, PetType type, LocalDate birthDay, Member member){
        this.name = name;
        this.category = category;
        this.type = type;
        this.birthday = birthDay;
        this.member = member;
    }
}
