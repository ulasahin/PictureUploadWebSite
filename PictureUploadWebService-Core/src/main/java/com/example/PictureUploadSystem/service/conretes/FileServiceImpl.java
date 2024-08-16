package com.example.PictureUploadSystem.service.conretes;

import com.example.PictureUploadSystem.core.exceptionhandling.exception.problemdetails.ErrorMessages;
import com.example.PictureUploadSystem.core.exceptionhandling.exception.types.BusinessException;
import com.example.PictureUploadSystem.model.entity.File;
import com.example.PictureUploadSystem.repository.FileRepository;
import com.example.PictureUploadSystem.service.abstracts.FileService;
import com.example.PictureUploadSystem.service.dtos.res.AddFileResponse;
import com.example.PictureUploadSystem.service.dtos.res.DeleteFileResponse;
import com.example.PictureUploadSystem.service.mappers.FileMapper;
import com.example.PictureUploadSystem.service.rules.FileBusinessRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class FileServiceImpl implements FileService {

    @Value("${file.upload.dir}")
    private String UPLOAD_DIR;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileBusinessRule fileBusinessRule;

    @Override
    public List<AddFileResponse> add(MultipartFile[] files) {
        List<AddFileResponse> responses = new ArrayList<>();

        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();

            String uniqueFilename = fileBusinessRule.generateUniqueFilename(originalFilename);

            Path filePath = fileBusinessRule.uploadFile(file, uniqueFilename);

            File newFile = new File();
            newFile.setFileName(uniqueFilename);
            newFile.setFilePath(filePath.toString());
            newFile.setUploadDate(LocalDateTime.now());
            fileRepository.save(newFile);

            responses.add(new AddFileResponse(newFile.getFileName(), newFile.getUploadDate()));
        }

        return responses;
    }

    @Override
    public DeleteFileResponse delete(long id) {
        File file = fileRepository.findById(id)
                .orElseThrow(()-> new BusinessException(ErrorMessages.FILE_NOT_FOUND));

        Path filePath = Paths.get(file.getFilePath());
        fileBusinessRule.deleteFile(filePath);

        DeleteFileResponse response = FileMapper.INSTANCE.fileFromDeleteFileResponse(file);
        fileRepository.delete(file);
        return response;
    }

    @Override
    public byte[] dowloadFile(long id) throws IOException {
        File file = fileRepository.findById(id).orElseThrow();
        Path path = Path.of(file.getFilePath());

        return Files.readAllBytes(path);
    }
}