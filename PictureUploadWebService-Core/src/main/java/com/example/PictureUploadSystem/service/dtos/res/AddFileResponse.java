package com.example.PictureUploadSystem.service.dtos.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddFileResponse {
    private String fileName;

    private LocalDateTime uploadDate;
}
