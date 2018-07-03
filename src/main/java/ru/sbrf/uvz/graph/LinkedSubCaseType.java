package ru.sbrf.uvz.graph;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

public class LinkedSubCaseType {
    @Getter
    private SubCaseType linkType;
    @Getter
    List<SubCaseInfo> subCaseInfoList;

    public LinkedSubCaseType(SubCaseType linkType) {
        this.linkType = linkType;
        this.subCaseInfoList = new LinkedList<>();
    }

    @Override
    public String toString() {
        return "LinkedSubCaseType{" +
                "linkType=" + linkType +
                ", subCaseInfoList=" + subCaseInfoList +
                '}';
    }
}
