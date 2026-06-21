package pe.cibertec.inkaproductos.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pe.cibertec.inkaproductos.security.JwtFilter;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Habilitamos CORS aquí para que Spring sepa cómo responder al navegador
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/productos/migrar-historico").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/stock/eventos").permitAll()
                        .requestMatchers("/ws/**", "/ws/almacenes.wsdl").permitAll()


                        // --- REGLAS DE PRODUCTOS ---
                        .requestMatchers(HttpMethod.GET, "/api/productos", "/api/productos/**").hasAnyRole("ADMIN", "SUPERVISOR", "USUARIO")
                        .requestMatchers("/api/productos/**").hasRole("ADMIN")

                        // --- OTRAS REGLAS ---
                        .requestMatchers("/api/usuarios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/traslados").hasAnyRole("ADMIN", "SUPERVISOR")
                        .requestMatchers("/api/solicitudes/*/aprobar", "/api/solicitudes/*/rechazar", "/api/solicitudes/pendientes").hasAnyRole("ADMIN", "SUPERVISOR")
                        .requestMatchers(HttpMethod.POST, "/api/solicitudes").hasAnyRole("ADMIN", "SUPERVISOR", "USUARIO")
                        .requestMatchers("/api/solicitudes/mis").hasAnyRole("ADMIN", "SUPERVISOR", "USUARIO")

                        // --- REGLAS DE SOPORTE (TICKETS) ---
                        .requestMatchers(HttpMethod.POST, "/api/soporte/mensajes").hasRole("SUPERVISOR")
                        .requestMatchers(HttpMethod.GET, "/api/soporte/mensajes/mis-tickets").hasRole("SUPERVISOR")
                        .requestMatchers(HttpMethod.PATCH, "/api/soporte/mensajes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/soporte/mensajes").hasRole("ADMIN")
                        .requestMatchers("/api/soporte/**").permitAll()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}