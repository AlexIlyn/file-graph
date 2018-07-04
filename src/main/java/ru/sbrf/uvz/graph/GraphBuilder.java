package ru.sbrf.uvz.graph;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GraphBuilder {

    public Graph buildFromString(String configuration) {
        Graph graph = null;
        try (Reader reader = new StringReader(configuration)) {
            graph = buildFromReader(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;

    }

    public Graph buildFromFile(String path) {
        Graph graph = null;
        try (Reader reader = new FileReader(Paths.get(path).toFile())) {
            graph = buildFromReader(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }

    private Graph buildFromReader(Reader reader) {
        Graph graph = new Graph();
        try (BufferedReader inputReader = new BufferedReader(reader)) {
            for (String line = inputReader.readLine(); line != null; line = inputReader.readLine()) {
                if (line.isEmpty() || line.charAt(0) == '#') continue;
                System.out.println(line);
                addNodesToGraph(graph, parseSubCaseTypes(line.split(">")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Node node : graph.getNodes()) {
            System.out.println(node);
            node.getNeighbors().forEach(System.out::println);
        }
        return graph;
    }

    private void addNodesToGraph(Graph graph, List<SubCaseType> subCaseTypes) {
        Node parentNode = null;
        Node childNode;
        for (SubCaseType currSubCaseType : subCaseTypes) {
            if (graph.containsNode(currSubCaseType)) {
                //System.out.printf("Нода %s найдена в графе.\n", currSubCaseType);
                if (parentNode == null) {
                    parentNode = graph.tryGetNode(currSubCaseType);
                    //System.out.println("Устанавливаем ноду как родительскую для следующей");
                } else {
                    childNode = graph.tryGetNode(currSubCaseType);
                    parentNode.addDependent(childNode);
                    //System.out.printf("[1]Добавляем ноду %s как дочернюю для ноды %s\n", childNode, parentNode);
                    parentNode = childNode;
                }
            } else {
                //System.out.printf("Нода %s не найдена в графе.\n", currSubCaseType);
                if (parentNode == null) {
                    //System.out.println("Добавляем ее в граф как корневую ноду.");
                    parentNode = new Node(currSubCaseType);
                    graph.addNode(parentNode);
                } else {
                    childNode = new Node(currSubCaseType);
                    //System.out.printf("[2]Добавляем ноду %s как дочернюю для ноды %s\n", childNode, parentNode);
                    parentNode = parentNode.addDependent(childNode);
                }
            }
        }
        System.out.println();
    }

    private List<SubCaseType> parseSubCaseTypes(String[] strings) throws IllegalArgumentException {
        List<SubCaseType> subCaseTypeList = new ArrayList<>();
        for (String string : strings) {
            SubCaseType subCaseType = SubCaseType.valueOf(string.trim());
            subCaseTypeList.add(subCaseType);
        }
        return subCaseTypeList;
    }
    //    public Graph buildFromFile(String path) {
//        Graph graph = new Graph();
//        try (BufferedReader inputReader = new BufferedReader(new FileReader(Paths.get(path).toFile()))) {
//            for (String line = inputReader.readLine(); line != null; line = inputReader.readLine()) {
//                if (line.isEmpty() || line.charAt(0) == '#') continue;
//                System.out.println(line);
//                addNodesToGraph(graph, parseSubCaseTypes(line.split(">")));
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return graph;
//    }
}
