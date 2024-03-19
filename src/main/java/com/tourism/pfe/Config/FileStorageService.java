package com.tourism.pfe.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@Service
public class FileStorageService {

    private String uploadDir = "src/main/resources/static/uploads";

    public List<String> storeMultipleFiles(List<MultipartFile> files) {
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            try {
                java.nio.file.Path uploadPath = java.nio.file.Paths.get(uploadDir);
                java.nio.file.Files.createDirectories(uploadPath);
                java.nio.file.Path targetLocation = uploadPath.resolve(fileName);
                java.nio.file.Files.copy(file.getInputStream(), targetLocation,
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                fileNames.add(fileName);
            } catch (Exception ex) {
                throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
            }
        }
        return fileNames;
    }

    public ResponseEntity<Resource> getFile(String fileName) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // Set the appropriate media type

                return new ResponseEntity<>(resource, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            throw new RuntimeException("File not found: " + fileName, ex);
        }
    }

    public List<Resource> getMultipleFiles(List<String> fileNames) {
        List<Resource> resources = new ArrayList<>();
        try {
            for (String fileName : fileNames) {
                System.out.println(fileName);
                Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
                Resource resource = new UrlResource(filePath.toUri());

                if (resource.exists()) {
                    resources.add(resource);
                }
            }
            return resources;
        } catch (Exception ex) {
            throw new RuntimeException("Error getting files", ex);
        }
    }
}
