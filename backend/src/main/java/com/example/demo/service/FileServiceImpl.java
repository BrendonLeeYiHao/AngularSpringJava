package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.FileStorageException;
import com.example.demo.model.FileEntity;
import com.example.demo.repository.FileRepository;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    public FileEntity uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new FileStorageException("File is empty");
        }

        try {
            FileEntity fileEntity = new FileEntity();
            fileEntity.setName(file.getOriginalFilename());
            fileEntity.setContent(file.getBytes());
            return fileRepository.save(fileEntity);
        } catch (IOException e) {
            throw new FileStorageException("Error occurred while storing the file", e);
        }
    }

    public ResponseEntity<byte[]> getFile(int id) {
        FileEntity fileEntity = fileRepository.findById(id).orElse(null);
        if (fileEntity != null) {
            byte[] fileContent = fileEntity.getContent();
            String contentType = "image/png";

            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, contentType);
            headers.setContentLength(fileContent.length);
            return new ResponseEntity<>(fileContent, HttpStatus.OK);
        } else {
            throw new NotFoundException("File Not found");
        }
    }

    public List<FileEntity> getAllFiles() {
        Sort sortById = Sort.by("id").ascending();
        List<FileEntity> fileEntities = fileRepository.findAll(sortById);
        return fileEntities;
    }

    public String deleteFile(int id) {
        FileEntity fileEntity = fileRepository.findById(id).orElse(null);
        if (fileEntity != null) {
            fileRepository.deleteById(id);
            return "Delete Successfully!";
        } else {
            throw new NotFoundException("File Not found");
        }
    }
}