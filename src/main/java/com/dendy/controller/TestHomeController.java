package com.dendy.controller;

import com.dendy.service.GenerateFileService;
import com.dendy.vo.FileVo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@Controller
public class TestHomeController {


    @Autowired
    private GenerateFileService pdfToExcelService;

    @RequestMapping(value = "home")
    public String home(){
        return "index";
    }

    @RequestMapping(value = "upload")
    @ResponseBody
    public ResponseEntity<byte[]> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("format") String format,@RequestParam("fileName") String fileName) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            byte[] bytes;
            String contentType;
            String extension;
            if ("csv".equalsIgnoreCase(format)) {
                bytes = pdfToExcelService.convertPdfToCsv(file);
                contentType = "text/csv";
                extension = "csv";
            } else {
                Workbook workbook = pdfToExcelService.convertPdfToExcel(file);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                workbook.write(out);
                bytes = out.toByteArray();
                contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                extension = "xlsx";
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType));
            headers.setContentDispositionFormData("attachment", fileName+"." + extension);
            headers.setContentLength(bytes.length);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(bytes);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
