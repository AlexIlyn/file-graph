package ru.sbrf.uvz.graph;

import java.util.List;

public class LinkedSubCaseType {
    private String linkType;
    List<SubCaseInfo> subCaseInfoList;

    public LinkedSubCaseType(String linkType, List<SubCaseInfo> subCaseInfoList) {
        this.linkType = linkType;
        this.subCaseInfoList = subCaseInfoList;
    }
}
