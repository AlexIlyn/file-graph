package ru.sbrf.uvz.graph;

import java.util.*;

public class Graph {

    private Map<SubCaseType, Node> nodes;

    public Graph() {
        this.nodes = new HashMap<>();
    }

    private List<LinkedSubCaseType> getTopNodes(List<SubCaseInfo> subCases) {
        return null;
    }

    List<SubCaseInfo> getSubCasesContentInGraph(List<SubCaseInfo> subCases) {
        return null;
    }

    List<LinkedSubCaseType> getLinkedSubCaseTypes(List<SubCaseInfo> subCases) {

        return null;
    }

    List<SubCaseInfo> getSingleSubCaseTypes(List<SubCaseInfo> subCases) {

        return null;
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
     * This method adds a Node to the graph. If a Node with the same label
     * as the parameter exists in the Graph, the existing Node is overwritten
     * only if overwriteExisting is true. If the existing Node is overwritten,
     * the Edges incident to it are all removed from the Graph.
     *
     * @param node
     * @return true if Node was added to the Graph
     */
    public boolean addNode(Node node) {
        Node current = this.nodes.get(node.getKey());
        if (current != null) {
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
