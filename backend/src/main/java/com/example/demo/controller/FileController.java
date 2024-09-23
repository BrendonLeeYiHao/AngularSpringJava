package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.FileEntity;
import com.example.demo.response.SuccessResponse;
import com.example.demo.service.FileService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<SuccessResponse<FileEntity>> uploadFile(@RequestParam MultipartFile file) {
        FileEntity fileEntity = fileService.uploadFile(file);
        return ResponseEntity.ok(new SuccessResponse<>(fileEntity, "File Uploaded Successfully!"));
    }
    
    @GetMapping("/get-file/{id}")
    public ResponseEntity<?> getFile(@PathVariable("id") int id) {
        return fileService.getFile(id);
    }

    @GetMapping("/get-files")
    public ResponseEntity<SuccessResponse<List<FileEntity>>> getAllFiles() {
        List<FileEntity> fileEntities = fileService.getAllFiles();
        return ResponseEntity.ok(new SuccessResponse<>(fileEntities, "Success"));
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<Void>> deleteFile(@PathVariable("id") int id) {
        String response = fileService.deleteFile(id);
        return ResponseEntity.ok(new SuccessResponse<>(null, response));
    }
}