package com.example.PictureUploadSystem.service.dtos.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddFileRequest {

    private String fileName;

    private LocalDateTime uploadDate;

    private String filePath;
}
