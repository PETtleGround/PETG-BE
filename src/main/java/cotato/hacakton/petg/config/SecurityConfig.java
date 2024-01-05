package cotato.hacakton.petg.config;

import cotato.hacakton.petg.config.filter.JwtAuthenticationFilter;
import cotato.hacakton.petg.config.filter.JwtAuthorizationFilter;
import cotato.hacakton.petg.config.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String[] WHITE_LIST = {
            "/v1/api/auth/join",
            "/login",
            "/v1/api/topic"
    };

    private final JwtUtil jwtUtil;
    private final CorsFilter corsFilter;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder sharedObject = http.getSharedObject(AuthenticationManagerBuilder.class);
        AuthenticationManager authenticationManager = sharedObject.build();
        http.authenticationManager(authenticationManager);

        http.csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .addFilter(new JwtAuthenticationFilter(authenticationManager, jwtUtil))
                .addFilterBefore(new JwtAuthorizationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
                .addFilter(corsFilter)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(WHITE_LIST).permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }

}
