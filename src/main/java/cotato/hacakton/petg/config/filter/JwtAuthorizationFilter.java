package cotato.hacakton.petg.config.filter;

import cotato.hacakton.petg.config.jwt.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private static final String AUTH_PATH = "/v1/api/auth";
    private static final String LOGIN_PATH = "/login";
    private static final String TOPIC_GET_PATH = "/v1/api/topic";

    private final JwtUtil jwtUtil;

    public JwtAuthorizationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String accessToken = jwtUtil.resolveAccessToken(request);
        log.info("액세스토큰 반환 완료: {}", accessToken);

        if (!jwtUtil.validateToken(accessToken)) {
            setAuthentication(accessToken);
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(String accessToken) {
        log.info("setAuthentication");
        String email = jwtUtil.getEmail(accessToken);
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(email, "", null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        log.info("진입한 path : {}", path);
        return path.startsWith(AUTH_PATH) || path.startsWith(LOGIN_PATH) || path.equals(TOPIC_GET_PATH);
    }
}
