package ru.sbrf.uvz.graph;


import java.util.*;
import java.util.stream.Collectors;

public class Graph {

    private Map<SubCaseType, Node> nodes;

    public Graph() {
        this.nodes = new HashMap<>();
    }

    private List<LinkedSubCaseType> getTopNodes(List<SubCaseInfo> subCases) {
        Map<SubCaseType, LinkedSubCaseType> result = new HashMap<>();
        HashSet<SubCaseType> parsedTypes = new HashSet<>();
        for (SubCaseInfo subCase : subCases) {
            Node node = tryGetNode(subCase.getSubCaseType());
            if (node.isRoot() && !result.containsKey(subCase.getSubCaseType())) {
                result.put(subCase.getSubCaseType(), new LinkedSubCaseType(subCase.getSubCaseType()));
            }
        }
        for (SubCaseInfo sc : subCases) {
            if (parsedTypes.contains(sc.getSubCaseType())) continue;
            parsedTypes.add(sc.getSubCaseType());
            boolean typeHaveParentFiles = haveParentFiles(sc.getSubCaseType(), subCases);
            if (!typeHaveParentFiles && !result.containsKey(sc.getSubCaseType())) {
                result.put(sc.getSubCaseType(), new LinkedSubCaseType(sc.getSubCaseType()));
            }
        }
        return new ArrayList<>(result.values());
    }

    private boolean haveParentFiles(SubCaseType subCaseType, List<SubCaseInfo> subCases) {
        Node node = tryGetNode(subCaseType);
        List<Node> parents = null;
        if (node != null) parents = node.getParentNodes();
        if (node == null || parents.size() == 0) return false;
        for (Node parent_node : parents) {
            for (SubCaseInfo sc : subCases) {
                if (sc.getSubCaseType() == parent_node.getKey()) return true;
            }
        }
        return false;
    }

    private List<SubCaseInfo> getSubCasesContentInGraph(List<SubCaseInfo> subCases) {

        return subCases.stream().filter(sc -> this.containsNode(sc.getSubCaseType())).collect(Collectors.toList());
    }

    public List<LinkedSubCaseType> getLinkedSubCaseTypes(List<SubCaseInfo> subCases) {
        List<LinkedSubCaseType> rootLinkedSubCases = getTopNodes(getSubCasesContentInGraph(subCases));
        List<SubCaseInfo> internalSubCasesList = new ArrayList<>(subCases);
        for (LinkedSubCaseType rootLinkedSubCase : rootLinkedSubCases) {
            LinkedSubCaseType tmp = getSubcaseDependentsHorizontal(internalSubCasesList, rootLinkedSubCase);
            internalSubCasesList.removeAll(tmp.getSubCaseInfoList());
        }
        return rootLinkedSubCases;
    }

    public List<SubCaseInfo> getSingleSubCaseTypes(List<SubCaseInfo> subCases) {

        return subCases.stream().filter(sc -> !this.containsNode(sc.getSubCaseType())).collect(Collectors.toList());
    }

    private LinkedSubCaseType getSubcaseDependentsHorizontal(List<SubCaseInfo> subCases, LinkedSubCaseType linkedSubCaseType) {
        Node currentNode = tryGetNode(linkedSubCaseType.getLinkType());
        Queue<Node> queue = new LinkedList<>();
        do {
            for (SubCaseInfo subCase : subCases) {
                if (subCase.getSubCaseType().equals(currentNode.getKey())) {
                    linkedSubCaseType.getSubCaseInfoList().add(subCase);
                }
            }
            queue.addAll(currentNode.getChildNodes());
            if (!queue.isEmpty()) currentNode = queue.poll();
        } while (!queue.isEmpty());
        return linkedSubCaseType;
    }

    boolean containsNode(SubCaseType subCaseType) {
        return this.nodes.containsKey(subCaseType);
    }

    Node tryGetNode(SubCaseType subCaseType) {
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
     * @param node node to add
     * @return true if Node was added to the Graph
     */
    boolean addNode(Node node) {
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
