package cotato.hacakton.petg.domain.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class JoinRequest {

    @Email
    private String email;
    @Size(min = 8, max = 16)
    private String password;
    private String nickName;
}
