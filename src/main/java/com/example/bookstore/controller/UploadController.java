package com.example.bookstore.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    private final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty())
            return null;

        File directory = new File(UPLOAD_DIR);
        if (!directory.exists())
            directory.mkdirs();

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + fileName);
        Files.write(path, file.getBytes());

        return "/uploads/" + fileName;
    }
}
