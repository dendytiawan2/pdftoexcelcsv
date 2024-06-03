package com.dendy.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface GenerateFileService {
    Workbook convertPdfToExcel(MultipartFile file) throws IOException;
    byte[] convertPdfToCsv(MultipartFile file) throws IOException;
}
