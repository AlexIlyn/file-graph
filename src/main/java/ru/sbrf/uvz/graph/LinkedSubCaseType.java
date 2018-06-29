package ru.sbrf.uvz.graph;

import java.util.List;

public class LinkedSubCaseType {
    private SubCaseType linkType;
    List<SubCaseInfo> subCaseInfoList;

    public LinkedSubCaseType(SubCaseType linkType, List<SubCaseInfo> subCaseInfoList) {
        this.linkType = linkType;
        this.subCaseInfoList = subCaseInfoList;
    }
}
