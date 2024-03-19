package com.tourism.pfe.Circuit;

import com.tourism.pfe.Circuit.SortieCircuit.SortieCircuit;
import com.tourism.pfe.Circuit.TypeCircuit;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Data
@Getter
@Setter
public class CircuitDTO {
    private String libelle;
    private String courtDescription;
    private String ville;
    private TypeCircuit type;
    private boolean active;
    private String description;
    private List<MultipartFile> images;
    private List<SortieCircuit> sortieCircuits;
}