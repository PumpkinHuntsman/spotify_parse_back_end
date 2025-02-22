package com.example.demo.controller;

import com.example.demo.service.FileStorageService;
import com.example.demo.service.SharedService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.io.File;

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


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        sharedService.setPublicString1("File -" + fileName);
        return ResponseEntity.ok("File uploaded successfully: " + fileName);
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
    public ResponseEntity<List<String>> getFilePreviews() {
        return ResponseEntity.ok(fileStorageService.getFilePreviews());
    }


}
