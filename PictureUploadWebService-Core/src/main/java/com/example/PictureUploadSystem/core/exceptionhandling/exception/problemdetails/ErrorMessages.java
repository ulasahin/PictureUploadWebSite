package com.example.PictureUploadSystem.core.exceptionhandling.exception.problemdetails;

public final class ErrorMessages {
    private ErrorMessages() {
        throw new UnsupportedOperationException("Bu sabit bir sınıftır ve örneklenemez.");
    }
    public static final String FILE_CANNOT_SAVE = "Dosya depolanamadı. Hata: ";
    public static final String FILE_CANNOT_DELETE = "Dosya fiziksel olarak silinemedi. Hata: ";
    public static final String FILE_NOT_FOUND = "Böyle bir dosya bulunamadı.";
}
