package com.tourism.pfe.User;

import com.tourism.pfe.Config.JWTService;
import com.tourism.pfe.Historique.Historique;
import com.tourism.pfe.Historique.HistoriqueService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final HistoriqueService historiqueService;
    private final JWTService jwtService;



    public User addUser(User user, HttpServletRequest request){
        Claims c = getClaims(request);
        historiqueService.addHistorique(
                Historique.builder()
                        .userRole(c.get("userRole").toString())
                        .userId(String.valueOf(c.get("userId")))
                        .userName(c.get("name").toString())
                        .date((new java.util.Date()).toString())
                        .action("Ajout de "+user.getName())
                        .build()
        );

        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private Claims getClaims(HttpServletRequest request) {
        var token = request.getHeader("Authorization").substring(7);
        return jwtService.extractAllClaimes(token);
    }

    public User getUser(int id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(int id,HttpServletRequest request){
        Claims c = getClaims(request);
        Historique.builder()
                        .date((new java.util.Date()).toString())
                        .userRole(c.get("userRole").toString())
                        .userId(String.valueOf(c.get("userId")))
                        .userName(c.get("name").toString())
                        .action("Suppression de "+getUser(id).getName())
                        .build();
        userRepository.deleteById(id);
    }

    public void updateUser(User user){
        User user1 = getUser(user.getId());
        if(user.getPassword()==null) user.setPassword(user1.getPassword());
        else user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void lockUser(int id){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            user.setLocked(true);
            userRepository.save(user);
        }
    }

    public void unlockUser(int id){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            user.setLocked(false);
            userRepository.save(user);
        }
    }

    public void updatePassword(int id, String password){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            user.setPassword(encoder.encode(password));
            userRepository.save(user);
        }
    }
}
