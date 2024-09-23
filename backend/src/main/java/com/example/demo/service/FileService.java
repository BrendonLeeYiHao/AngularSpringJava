package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.FileEntity;

public interface FileService {

    public FileEntity uploadFile(MultipartFile file);
    public ResponseEntity<byte[]> getFile(int id);
    public List<FileEntity> getAllFiles();
    public String deleteFile(int id);
}