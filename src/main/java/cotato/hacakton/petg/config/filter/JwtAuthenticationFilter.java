package cotato.hacakton.petg.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import cotato.hacakton.petg.config.auth.PrincipalDetails;
import cotato.hacakton.petg.config.jwt.JwtUtil;
import cotato.hacakton.petg.config.jwt.Token;
import cotato.hacakton.petg.domain.entity.Member;
import cotato.hacakton.petg.exception.AppException;
import cotato.hacakton.petg.exception.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Value("${jwt.refresh.expiration}")
    private int refreshTokenAge;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        log.info("login POST 요청");

        ObjectMapper mapper = new ObjectMapper();
        try {
            Member member = mapper.readValue(request.getInputStream(), Member.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    member.getEmail(), member.getPassword());
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.LOGIN_FAIL);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        PrincipalDetails principal = (PrincipalDetails) authResult.getPrincipal();

        Token token = jwtUtil.createToken(principal.getUsername());

        String accessToken = token.getAccessToken();
        response.addHeader("accessToken", accessToken);


        Cookie cookie = new Cookie("refreshToken", token.getRefreshToken());
        cookie.setPath("/");
        cookie.setMaxAge(refreshTokenAge);
        cookie.setSecure(true);
        response.addCookie(cookie);
        log.info("로그인 성공, JWT 토큰 생성");
    }
}
