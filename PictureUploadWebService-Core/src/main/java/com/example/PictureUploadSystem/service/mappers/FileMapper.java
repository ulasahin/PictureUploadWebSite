package com.example.PictureUploadSystem.service.mappers;

import com.example.PictureUploadSystem.model.entity.File;
import com.example.PictureUploadSystem.service.dtos.res.DeleteFileResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileMapper {
    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);

    DeleteFileResponse fileFromDeleteFileResponse(File file);
}
