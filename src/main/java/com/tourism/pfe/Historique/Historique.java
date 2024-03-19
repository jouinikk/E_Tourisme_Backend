package com.tourism.pfe.Historique;

import com.tourism.pfe.Config.JWTService;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Historique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private String date;

    private String action;

    private String userId;

    private String userName;

    private String userRole;

    private String adresseIp;

    @PrePersist
    public void prePersist() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        adresseIp = request.getRemoteAddr();
    }
}