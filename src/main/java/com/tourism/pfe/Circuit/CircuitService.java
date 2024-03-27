package com.tourism.pfe.Circuit;

import com.tourism.pfe.Circuit.ImageCircuit.ImageCircuit;
import com.tourism.pfe.Circuit.ImageCircuit.ImageCircuitRepository;
import com.tourism.pfe.Circuit.SortieCircuit.SortieCircuit;
import com.tourism.pfe.Circuit.SortieCircuit.SortieCircuitRepository;
import com.tourism.pfe.Config.CloudinaryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CircuitService {
    private final CircuitRepository circuitRepository;
    private final SortieCircuitRepository sortieCircuitRepository;
    private final CloudinaryService cloudinaryService;
    private final ImageCircuitRepository imageCircuitRepository;
    public List<Circuit> getAllCircuits() {
        return circuitRepository.findAll();
    }
    public List<SortieCircuit> getSortieCircuitsByCircuitId(int id) {
        return sortieCircuitRepository.findByCircuitId(id);
    }
    public List<ImageCircuit> getImagesByCircuitId(int id){
        return imageCircuitRepository.findImageCircuitByCircuitId(id);
    }


    @Transactional
        public void createCircuit(CircuitDTO circuitDTO) throws IOException {
            Circuit circuit = new Circuit();
            circuit.setLibelle(circuitDTO.getLibelle());
            circuit.setCourtDescription(circuitDTO.getCourtDescription());
            circuit.setVille(circuitDTO.getVille());
            circuit.setType(circuitDTO.getType());
            circuit.setActive(circuitDTO.isActive());
            circuit.setDescription(circuitDTO.getDescription());

            circuit = circuitRepository.save(circuit);

            if(circuitDTO.getImages()!=null){
                for (MultipartFile file : circuitDTO.getImages()) {
                    BufferedImage bi = ImageIO.read(file.getInputStream());
                    if (bi == null){
                        throw new IOException();
                    }
                    Map result = cloudinaryService.upload(file);
                    ImageCircuit imageCircuit = new ImageCircuit();
                    imageCircuit.setCircuit(circuit);
                    imageCircuit.setImageUrl((String) result.get("url"));

                    // Save each ImageCircuit entity before adding it to the list
                    imageCircuitRepository.save(imageCircuit);
                }
            }
            if(circuitDTO.getSortieCircuits()!=null){
                for (SortieCircuit sortieCircuit: circuitDTO.getSortieCircuits()){
                    sortieCircuit.setCircuit(circuit);
                    sortieCircuitRepository.save(sortieCircuit);
                }
            }

    }

    public List<CircuitDTOResponse> getAllCircuitsWithSortiesAndImages(){
        List<Circuit> circuits = circuitRepository.findCircuitsByActiveIsTrue();
        List<CircuitDTOResponse> response = new ArrayList<>();
        for (Circuit circuit: circuits){
            CircuitDTOResponse dto = new CircuitDTOResponse();
            dto.setImages(imageCircuitRepository.findImageCircuitByCircuitId(circuit.getId()));
            dto.setSortieCircuits(sortieCircuitRepository.findByCircuitId(circuit.getId()));
            dto.setLibelle(circuit.getLibelle());
            dto.setDescription(circuit.getDescription());
            dto.setType(circuit.getType());
            dto.setActive(circuit.isActive());
            dto.setVille(circuit.getVille());
            dto.setCourtDescription(circuit.getCourtDescription());
            response.add(dto);
        }
        return response;
    }

    public CircuitDTOResponse getOneWithSortiesAndImages(int id){
        Circuit circuit = circuitRepository.findById(id).get();
        if (!circuit.isActive()) return null;
        CircuitDTOResponse response= new CircuitDTOResponse();
        response.setImages(imageCircuitRepository.findImageCircuitByCircuitId(id));
        response.setSortieCircuits(sortieCircuitRepository.findByCircuitId(id));
        response.setType(circuit.getType());
        response.setDescription(circuit.getDescription());
        response.setCourtDescription(circuit.getCourtDescription());
        response.setActive(circuit.isActive());
        response.setVille(circuit.getVille());
        return response;
    }
}
