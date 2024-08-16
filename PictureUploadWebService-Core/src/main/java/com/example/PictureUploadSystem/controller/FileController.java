package com.example.PictureUploadSystem.controller;

import com.example.PictureUploadSystem.service.abstracts.FileService;
import com.example.PictureUploadSystem.service.dtos.res.AddFileResponse;
import com.example.PictureUploadSystem.service.dtos.res.DeleteFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public List<AddFileResponse> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        return fileService.add(files);
    }
    @DeleteMapping("/delete")
    public DeleteFileResponse delete(@RequestParam long id){
        return fileService.delete(id);
    }
    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public byte[] dowloadFile(@RequestParam long id) throws IOException {
        return fileService.dowloadFile(id);
    }
}
