package com.dendy.service.impl;

import com.dendy.service.GenerateFileService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class GenerateFileServiceImpl implements GenerateFileService {

    public byte[] convertPdfToCsv(MultipartFile file) throws IOException{

        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        document.close();

        StringBuilder csvOutput = new StringBuilder();
        String[] lines = text.split("\n");
        for (String line : lines) {
            String[] cells = line.split("\\s+"); // Adjust the delimiter as per your needs
            csvOutput.append(String.join(",", cells)).append("\n");
        }

        return csvOutput.toString().getBytes(StandardCharsets.UTF_8);

    }
    public Workbook convertPdfToExcel(MultipartFile file) throws IOException {
            PDDocument document = PDDocument.load(file.getInputStream());
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);
            document.close();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("PDF Data");

            String[] lines = text.split("\n");
            for (int i = 0; i < lines.length; i++) {
                Row row = sheet.createRow(i);
                String[] cells = lines[i].split("\\s+"); // Adjust the delimiter as per your needs
                for (int j = 0; j < cells.length; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(cells[j]);
                }
            }

            return workbook;
    }
}
