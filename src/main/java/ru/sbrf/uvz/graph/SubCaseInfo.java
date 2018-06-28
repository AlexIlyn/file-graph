package ru.sbrf.uvz.graph;


import org.springframework.util.StringUtils;

public class SubCaseInfo {

    public SubCaseInfo() {
    }

    public SubCaseInfo(String fileUrl, SubCaseType subCaseType) {
        this.fileUrl = fileUrl;
        this.subCaseType = subCaseType;
    }

    private SubCaseType subCaseType;

    private String fileUrl;

    public boolean isValid() {
        return StringUtils.hasText(fileUrl) && subCaseType != null;
    }

}
