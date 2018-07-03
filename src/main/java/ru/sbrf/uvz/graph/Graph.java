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
        List<SubCaseInfo> internalList = new ArrayList<>(subCases);
        //System.out.println("[INTERNAL LIST] " + internalList);
        HashSet<SubCaseType> parsedTypes = new HashSet<>();
        for (SubCaseInfo subCase : subCases) {
            var node = tryGetNode(subCase.getSubCaseType());
            if (node.isRoot() && !result.containsKey(subCase.getSubCaseType())) {
                result.put(subCase.getSubCaseType(), new LinkedSubCaseType(subCase.getSubCaseType()));
                internalList = internalList.stream().filter(sc -> sc.getSubCaseType() != subCase.getSubCaseType()).collect(Collectors.toList());
                //System.out.println("[INTERNAL LIST] " + internalList);
            }
        }
        internalList = removeCildsFromList(internalList, result.values().stream().collect(Collectors.toList()));
        for (SubCaseInfo sc : internalList) {
            if (parsedTypes.contains(sc.getSubCaseType())) continue;
            parsedTypes.add(sc.getSubCaseType());
            boolean typeHaveParentFiles = haveParentFiles(sc.getSubCaseType(), subCases);
            if (!typeHaveParentFiles && !result.containsKey(sc.getSubCaseType())) {
                result.put(sc.getSubCaseType(), new LinkedSubCaseType(sc.getSubCaseType()));
                //internalList = internalList.stream().filter(i -> i.getSubCaseType() != sc.getSubCaseType()).collect(Collectors.toList());
            }
        }
        //System.out.println("GETTOPNODES##################################\n" + result.values());
        return new ArrayList<>(result.values());
    }

    protected List<SubCaseInfo> removeCildsFromList(List<SubCaseInfo> subCases, List<LinkedSubCaseType> linkedSubCaseTypes) {
        var internalList = new ArrayList<SubCaseInfo>(subCases);
        for (var linkedSubCaseType : linkedSubCaseTypes) {
            Node currentNode = tryGetNode(linkedSubCaseType.getLinkType());
            Queue<Node> queue = new LinkedList<>();
            do {
                for (SubCaseInfo subCase : subCases) {
                    if (subCase.getSubCaseType().equals(currentNode.getKey())) {
                        internalList.remove(subCase);
                    }
                }
                List<Node> childs = currentNode.getChildNodes();
                for (var node : childs) {
                    queue.add(node);
                }
                if (!queue.isEmpty()) currentNode = queue.poll();
            } while (!queue.isEmpty());
        }
        return internalList;
    }

    private boolean haveParentFiles(SubCaseType subCaseType, List<SubCaseInfo> subCases) {
        var node = tryGetNode(subCaseType);
        List<Node> parents = null;
        if (node != null) parents = node.getParentNodes();
        if (node == null || parents.size() == 0) return false;
        for (var parent_node : parents) {
            for (var sc : subCases) {
                if (sc.getSubCaseType() == parent_node.getKey()) return true;
            }
        }
        return false;
    }

    protected List<SubCaseInfo> getSubCasesContentInGraph(List<SubCaseInfo> subCases) {

        return subCases.stream().filter(sc -> this.containsNode(sc.getSubCaseType())).collect(Collectors.toList());
    }

    public List<LinkedSubCaseType> getLinkedSubCaseTypes(List<SubCaseInfo> subCases) {
        var rootLinkedSubCases = getTopNodes(getSubCasesContentInGraph(subCases));
        var internalSubCasesList = new ArrayList<SubCaseInfo>(subCases);
        for (int i = 0; i < rootLinkedSubCases.size(); i++) {
            var tmp = getSubcaseDependentsHorizontal(internalSubCasesList, rootLinkedSubCases.get(i));
            internalSubCasesList.removeAll(tmp.getSubCaseInfoList());
        }
        return rootLinkedSubCases;
    }

    public List<SubCaseInfo> getSingleSubCaseTypes(List<SubCaseInfo> subCases) {

        return subCases.stream().filter(sc -> !this.containsNode(sc.getSubCaseType())).collect(Collectors.toList());
    }

    protected LinkedSubCaseType getSubcaseDependentsHorizontal(List<SubCaseInfo> subCases, LinkedSubCaseType linkedSubCaseType) {
        Node currentNode = tryGetNode(linkedSubCaseType.getLinkType());
        Queue<Node> queue = new LinkedList<>();
        do {
            for (SubCaseInfo subCase : subCases) {
                if (subCase.getSubCaseType().equals(currentNode.getKey())) {
                    linkedSubCaseType.getSubCaseInfoList().add(subCase);
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
