package ru.sbrf.uvz.graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GraphTest {
    @Test
    //@Ignore
    public void TestGetLinkedSubCaseTypes() {
        List<SubCaseInfo> subCaseList = new ArrayList<>();
        subCaseList.add(new SubCaseInfo("", SubCaseType.DRPA_CUST));
        subCaseList.add(new SubCaseInfo("", SubCaseType.DRPA_FILE2));
        subCaseList.add(new SubCaseInfo("", SubCaseType.DRPA_FILE2));
        subCaseList.add(new SubCaseInfo("", SubCaseType.DRPA_FILE));
        subCaseList.add(new SubCaseInfo("", SubCaseType.DRPA_DOCS));
        subCaseList.add(new SubCaseInfo("", SubCaseType.DRPA_DOCS));
        subCaseList.add(new SubCaseInfo("", SubCaseType.DRPA_CUST));
        subCaseList.add(new SubCaseInfo("", SubCaseType.SAPBO_CLIENT));
        subCaseList.add(new SubCaseInfo("", SubCaseType.SAPBO_DOCS));
        subCaseList.add(new SubCaseInfo("", SubCaseType.SAPBO_CLIENT));
        subCaseList.add(new SubCaseInfo("", SubCaseType.SAPBO_AGR_D));
//        subCaseList.add(new SubCaseInfo("", SubCaseType.SAPBO_CUST));
//        subCaseList.add(new SubCaseInfo("", SubCaseType.SAPBO_CUST));

        GraphBuilder graphBuilder = new GraphBuilder();
        Graph graph = graphBuilder.buildFromFile("graphdata");
        System.out.println("---------------------------------------------------------");
        graph.getSingleSubCaseTypes(subCaseList).forEach(System.out::println);
//        System.out.println("---------------------------------------------------------");
//        graph.getSubCasesContentInGraph(subCaseList).forEach(System.out::println);
//        System.out.println("---------------------------------------------------------");
//        graph.getTopNodes(graph.getSubCasesContentInGraph(subCaseList)).forEach(System.out::println);
//        System.out.println("---------------------------------------------------------");
//        System.out.println(graph.getSubcaseDependentsHorizontal(subCaseList, graph.getTopNodes(graph.getSubCasesContentInGraph(subCaseList)).get(0)));
        System.out.println("---------------------------------------------------------");
        graph.getLinkedSubCaseTypes(subCaseList).forEach(System.out::println);
    }
}
