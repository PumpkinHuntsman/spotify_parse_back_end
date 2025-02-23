package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    private final Path fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();

    public FileStorageService() {
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory", e);
        }
    }

    public String storeFile(MultipartFile file) {
        try {
            Path targetLocation = fileStorageLocation.resolve(Objects.requireNonNull(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return file.getOriginalFilename();
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + file.getOriginalFilename(), e);
        }
    }

    public List<String> listFiles() {
        try (Stream<Path> files = Files.list(fileStorageLocation)) {
            return files.map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Could not list files", e);
        }
    }

    /**
     * Reads the first 20 characters of each uploaded file.
     */
    public List<Map<String, String>> getFilePreviews() {
        try (Stream<Path> files = Files.list(fileStorageLocation)) {
            return files.map(file -> {
                Map<String, String> filePreview = new HashMap<>();
                filePreview.put("fileName", file.getFileName().toString());
                filePreview.put("preview", readFirst20Chars(file));
                return filePreview;
            }).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Could not read file previews", e);
        }
    }


    /**
     * Reads the first 20 characters of a given file.
     */
    private String readFirst20Chars(Path filePath) {
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            char[] buffer = new char[200];
            int bytesRead = reader.read(buffer, 0, 200);
            return bytesRead > 0 ? new String(buffer, 0, bytesRead) : "(Empty File)";
        } catch (IOException e) {
            return "(Error reading file)";
        }
    }


}
