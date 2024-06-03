package com.dendy.vo;

import java.io.Serializable;

public class FileVo implements Serializable {
    private String fileName;
    private String fileTest;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileTest() {
        return fileTest;
    }

    public void setFileTest(String fileTest) {
        this.fileTest = fileTest;
    }
}
