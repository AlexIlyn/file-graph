package ru.sbrf.uvz.graph;

import lombok.var;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {

    private Map<SubCaseType, Node> nodes;

    public Graph() {
        this.nodes = new HashMap<>();
    }

    protected List<LinkedSubCaseType> getTopNodes(List<SubCaseInfo> subCases) {
        Map<SubCaseType, LinkedSubCaseType> result = new HashMap();
        for (SubCaseInfo subCase : subCases) {
            var node = tryGetNode(subCase.getSubCaseType());
            if (node.isRoot() && !result.containsKey(subCase.getSubCaseType()))
                result.put(subCase.getSubCaseType(), new LinkedSubCaseType(subCase.getSubCaseType()));
        }
        return new ArrayList<>(result.values());
    }

    protected List<SubCaseInfo> getSubCasesContentInGraph(List<SubCaseInfo> subCases) {

        return subCases.stream().filter(sc -> this.containsNode(sc.getSubCaseType())).collect(Collectors.toList());
    }

    public List<LinkedSubCaseType> getLinkedSubCaseTypes(List<SubCaseInfo> subCases) {
        var rootLinkedSubCases = getTopNodes(getSubCasesContentInGraph(subCases));
        for (int i = 0; i < rootLinkedSubCases.size(); i++) {
            getSubcaseDependentsHorizontal(subCases, rootLinkedSubCases.get(i));
        }
        return rootLinkedSubCases;
    }

    public List<SubCaseInfo> getSingleSubCaseTypes(List<SubCaseInfo> subCases) {

        return subCases.stream().filter(sc -> !this.containsNode(sc.getSubCaseType())).collect(Collectors.toList());
    }

    //    protected LinkedSubCaseType getSubcaseDependentsVertical(List<SubCaseInfo> subCases, LinkedSubCaseType linkedSubCaseType) {
//        Stack<Node> nodesToCheck = new Stack<>();
//        Node currentNode = tryGetNode(linkedSubCaseType.getLinkType());
//        List<SubCaseInfo> internalSubCases = new ArrayList<>();
//        internalSubCases.addAll(subCases);
//        while (!nodesToCheck.isEmpty() || currentNode != null) {
//            if (!nodesToCheck.empty()) {
//                currentNode = nodesToCheck.pop();
//                System.out.println("POP");
//            }
//            while (currentNode != null) {
//                //System.out.println(currentNode.getKey());
//                for (SubCaseInfo subCase : internalSubCases) {
//                    if (subCase.getSubCaseType().equals(currentNode.getKey())){
//                        linkedSubCaseType.getSubCaseInfoList().add(subCase);
////                       internalSubCases.remove(subCase);
//                    }
//                }
//
//                List<Node> childs = currentNode.getChildNodes();
//                if (childs.size() == 0) {
//                    currentNode = null;
//                    continue;
//                } else if(childs.size()>=1){
//                    currentNode = childs.get(0);
//                    for (int i = 1; i < childs.size(); i++){
//                        nodesToCheck.push(childs.get(i));
//                    }
//                }
//            }
//        }
//        return linkedSubCaseType;
//    }
    protected LinkedSubCaseType getSubcaseDependentsHorizontal(List<SubCaseInfo> subCases, LinkedSubCaseType linkedSubCaseType) {
        Stack<Node> nodesToCheck = new Stack<>();
        Node currentNode = tryGetNode(linkedSubCaseType.getLinkType());
        List<SubCaseInfo> internalSubCases = new ArrayList<>();
        internalSubCases.addAll(subCases);
//        while (!nodesToCheck.isEmpty() || currentNode != null) {
//            if (!nodesToCheck.empty()) {
//                currentNode = nodesToCheck.pop();
//                System.out.println("POP");
//            }
//            while (currentNode != null) {
//                //System.out.println(currentNode.getKey());
//                for (SubCaseInfo subCase : internalSubCases) {
//                    if (subCase.getSubCaseType().equals(currentNode.getKey())){
//                        linkedSubCaseType.getSubCaseInfoList().add(subCase);
////                       internalSubCases.remove(subCase);
//                    }
//                }
//
//                List<Node> childs = currentNode.getChildNodes();
//                if (childs.size() == 0) {
//                    currentNode = null;
//                    continue;
//                } else if(childs.size()>=1){
//                    currentNode = childs.get(0);
//                    for (int i = 1; i < childs.size(); i++){
//                        nodesToCheck.push(childs.get(i));
//                    }
//                }
//            }
//        }
        Queue<Node> queue = new LinkedList<>();
        do {
            for (SubCaseInfo subCase : internalSubCases) {
                if (subCase.getSubCaseType().equals(currentNode.getKey())) {
                    linkedSubCaseType.getSubCaseInfoList().add(subCase);
//                       internalSubCases.remove(subCase);
                }
            }
            List<Node> childs = currentNode.getChildNodes();
            for (var node : childs) {
                queue.add(node);
            }
            if (!queue.isEmpty()) currentNode = queue.poll();
        } while (!queue.isEmpty());
        return linkedSubCaseType;
    }

    public boolean containsNode(SubCaseType subCaseType) {
        return this.nodes.containsKey(subCaseType);
    }

    public Node tryGetNode(SubCaseType subCaseType) {
        if (!this.nodes.containsKey(subCaseType)) return null;
        return this.nodes.get(subCaseType);
    }

    /**
     * @param subCaseType The specified Node label
     * @return Node The Node with the specified label
     */
    public Node getNode(SubCaseType subCaseType) {
        return nodes.get(subCaseType);
    }

    /**
     * This method adds a Node to the graph.     *
     *
     * @param node
     * @return true if Node was added to the Graph
     */
    public boolean addNode(Node node) {
        Node current = this.nodes.get(node.getKey());
        if (current != null || node.getGraph() != null) {
            return false;
        }
        nodes.put(node.getKey(), node);
        node.setGraph(this);
        return true;
    }

    /**
     * @param subCaseType The label of the Node to remove
     * @return Node The removed Node object
     */
    public Node removeNode(SubCaseType subCaseType) {
        return nodes.remove(subCaseType);
    }

    /**
     * @return Set<String> The unique labels of the Graph's Node objects
     */
    public Set<SubCaseType> NodeKeys() {
        return this.nodes.keySet();
    }

    public Collection<Node> getNodes() {
        return this.nodes.values();
    }

}
