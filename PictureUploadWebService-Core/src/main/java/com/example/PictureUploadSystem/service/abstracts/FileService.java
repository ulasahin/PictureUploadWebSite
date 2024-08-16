package com.example.PictureUploadSystem.service.abstracts;

import com.example.PictureUploadSystem.service.dtos.res.AddFileResponse;
import com.example.PictureUploadSystem.service.dtos.res.DeleteFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<AddFileResponse> add(MultipartFile[] file);
    DeleteFileResponse delete(long id);
    byte[] dowloadFile(long id) throws IOException;
}
