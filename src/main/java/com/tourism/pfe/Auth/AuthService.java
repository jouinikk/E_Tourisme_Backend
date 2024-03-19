package com.tourism.pfe.Auth;
import com.tourism.pfe.Config.JWTService;
import com.tourism.pfe.Historique.Historique;
import com.tourism.pfe.Historique.HistoriqueRepository;
import com.tourism.pfe.Historique.HistoriqueService;
import com.tourism.pfe.User.Role;
import com.tourism.pfe.User.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.tourism.pfe.User.User;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final HistoriqueService historiqueService;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthenticatonResponse register(RegisterRequest request) {
        try {
            logger.debug("Received register request: {}", request);
         var user = User.builder()
                 .name(request.getName())
                 .email(request.getEmail())
                 .role(Role.valueOf(request.getRole()))
                 .password(passwordEncoder.encode(request.getPassword()))
                 .build();
         repository.save(user);

         var jwtToken = jwtService.generateToken(user);
         return  AuthenticatonResponse.builder()
                 .token(jwtToken)
                 .build();
        } catch (Exception e) {
            logger.error("An error occurred during registration: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to register user", e);
        }
    }

    public AuthenticatonResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow(()-> new RuntimeException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        historiqueService.addHistorique(Historique.builder()
                .action("login")
                .userId(String.valueOf(user.getId()))
                .userName(user.getName())
                .date((new Date()).toString())
                .userRole(user.getRole().name())
                .build());
        return  AuthenticatonResponse.builder()
                .token(jwtToken)
                .build();
    }
}
