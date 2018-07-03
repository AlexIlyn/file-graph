package ru.sbrf.uvz.graph;


import lombok.Getter;
import org.springframework.util.StringUtils;

public class SubCaseInfo {

    public SubCaseInfo() {
    }

    public SubCaseInfo(String fileUrl, SubCaseType subCaseType) {
        this.fileUrl = fileUrl;
        this.subCaseType = subCaseType;
    }

    @Getter
    private SubCaseType subCaseType;
    @Getter
    private String fileUrl;

    public boolean isValid() {
        return StringUtils.hasText(fileUrl) && subCaseType != null;
    }

    @Override
    public String toString() {
        return "\nSubCase{" +
                "type=" + subCaseType +
                '}';
    }
}
