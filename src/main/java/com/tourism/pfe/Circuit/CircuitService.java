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

    public Circuit addCircuitWithSortieCircuits(Circuit circuit, List<SortieCircuit> sortieCircuits) {
        // Save the Circuit to get its ID
        Circuit savedCircuit = circuitRepository.save(circuit);

        // Set the Circuit for each SortieCircuit and save them
        for (SortieCircuit sortieCircuit : sortieCircuits) {
            sortieCircuit.setCircuit(savedCircuit);
            sortieCircuitRepository.save(sortieCircuit);
        }

        // Return the saved Circuit with its associated SortieCircuits
        return savedCircuit;
    }

    public Circuit addCircuitWithSortieCircuitsandImages(Circuit circuit, List<SortieCircuit> sortieCircuits, List<MultipartFile> imagesCircuit) throws IOException {
        // Save the Circuit to get its ID
        Circuit savedCircuit = circuitRepository.save(circuit);

        // Set the Circuit for each SortieCircuit and save them
        for (SortieCircuit sortieCircuit : sortieCircuits) {
            sortieCircuit.setCircuit(savedCircuit);
            sortieCircuitRepository.save(sortieCircuit);
        }

        for (MultipartFile image:imagesCircuit){
            BufferedImage bi = ImageIO.read(image.getInputStream());
            if (bi == null) {
                throw new IOException();
            }
            Map result = cloudinaryService.upload(image);
            ImageCircuit imageCircuit = new ImageCircuit();
            imageCircuit.setImageUrl((String) result.get("url"));
            imageCircuit.setCircuit(savedCircuit);
            imageCircuitRepository.save(imageCircuit);
        }

        // Return the saved Circuit with its associated SortieCircuits
        return savedCircuit;
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

}
