package com.example.PictureUploadSystem.service.rules;

import com.example.PictureUploadSystem.core.exceptionhandling.exception.problemdetails.ErrorMessages;
import com.example.PictureUploadSystem.core.exceptionhandling.exception.types.BusinessException;
import com.example.PictureUploadSystem.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileBusinessRule {

    @Value("${file.upload.dir}")
    private String UPLOAD_DIR;

    public String generateUniqueFilename(String originalFilename) {

        String extension = "";
        int dotIndex = originalFilename.lastIndexOf('.');
        if (dotIndex > 0) {
            extension = originalFilename.substring(dotIndex);
        }

        String uniqueID = UUID.randomUUID().toString();

        return originalFilename.replace(extension, "") + "_" + uniqueID + extension;
    }

    public Path uploadFile(MultipartFile file, String uniqueFilename){
        Path filePath = Paths.get(UPLOAD_DIR + uniqueFilename);

        try {
            if (!Files.exists(Paths.get(UPLOAD_DIR))) {
                Files.createDirectories(Paths.get(UPLOAD_DIR));
            }
            Files.write(filePath, file.getBytes());
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();

            throw new BusinessException(ErrorMessages.FILE_CANNOT_SAVE + e.getMessage());
        }
    }
    public void deleteFile(Path filePath){
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(ErrorMessages.FILE_CANNOT_DELETE + e.getMessage());
        }
    }
}
