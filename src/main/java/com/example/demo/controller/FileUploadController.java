package com.example.demo.controller;

import com.example.demo.service.FileStorageService;
import com.example.demo.service.SharedService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "http://localhost:3000")
public class FileUploadController {

    private final FileStorageService fileStorageService;
    private final SharedService sharedService;

    public FileUploadController(FileStorageService fileStorageService, SharedService sharedService) {
        this.fileStorageService = fileStorageService;
        this.sharedService = sharedService;
    }

    // Modified to handle multiple file uploads
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        StringBuilder uploadedFilesNames = new StringBuilder();
        boolean at_least_one_file_incorrect = false;
        for (MultipartFile file : files) {
            try {
                String fileName = fileStorageService.storeFile(file);
                uploadedFilesNames.append(fileName).append(", ");
                sharedService.setPublicString1("File - " + fileName);
                sharedService.processFileUpload(file);
            } catch (RuntimeException e) {
                sharedService.setPublicString1("File : " + file.getOriginalFilename() + " Is not an accepted Json file\n");
                at_least_one_file_incorrect = true;
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error with file: " + file.getOriginalFilename() + ". " + e.getMessage());
            }
        }
        if (at_least_one_file_incorrect) {
            return ResponseEntity.badRequest().body("At least one file is not compatible");
        } else {
            return ResponseEntity.ok("Files uploaded successfully: " + uploadedFilesNames);
        }

    }

    @GetMapping
    public ResponseEntity<List<String>> listFiles() {
        return ResponseEntity.ok(fileStorageService.listFiles());
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<FileSystemResource> downloadFile(@PathVariable String fileName) {
        File file = new File("uploads/" + fileName);
        FileSystemResource resource = new FileSystemResource(file);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .body(resource);
    }

    @GetMapping("/previews")
    public ResponseEntity<List<Map<String, String>>> getFilePreviews() {
        return ResponseEntity.ok(fileStorageService.getFilePreviews());
    }
}
