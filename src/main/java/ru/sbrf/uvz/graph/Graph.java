package ru.sbrf.uvz.graph;

import java.util.*;

public class Graph {

    private Map<SubCaseType, Node> nodes;

    public Graph(){
        this.nodes = new HashMap<SubCaseType, Node>();
    }

    private List<LinkedSubCaseType> getTopNodes(List<SubCaseInfo> subCases){
        return  null;
    }
    List<SubCaseInfo> getSubcasesContentInGraph(List<SubCaseInfo> subCases){
        return  null;
    }

    List<LinkedSubCaseType> getLinkedSubCaseTypes(List<SubCaseInfo> subCases) {

        return null;
    }

    List<SubCaseInfo> getSingleSubCaseTypes(List<SubCaseInfo> subCases) {

        return null;
    }

    /**
     * This constructor accepts an ArrayList<Node> and populates
     * this.vertices. If multiple Node objects have the same label,
     * then the last Node with the given label is used.
     *
     * @param nodes The initial Vertices to populate this Graph
     */
    public Graph(ArrayList<Node> nodes){
        this.nodes = new HashMap<>();

        for(Node v: nodes){
            this.nodes.put(v.getLabel(), v);
        }

    }

    /**
     *
     * @param Node The Node to look up
     * @return true iff this Graph contains Node
     */
    public boolean tryGetNode(Node Node){
        return this.nodes.get(Node.getLabel()) != null;
    }

    public Node tryGetNode(SubCaseType subCaseType){
        if(!this.nodes.containsKey(subCaseType)) return null;
        return this.nodes.get(subCaseType);
    }

    /**
     *
     * @param subCaseType The specified Node label
     * @return Node The Node with the specified label
     */
    public Node getNode(SubCaseType subCaseType){
        return nodes.get(subCaseType);
    }

    /**
     * This method adds a Node to the graph. If a Node with the same label
     * as the parameter exists in the Graph, the existing Node is overwritten
     * only if overwriteExisting is true. If the existing Node is overwritten,
     * the Edges incident to it are all removed from the Graph.
     *
     * @param Node
     * @param overwriteExisting
     * @return true if Node was added to the Graph
     */
    public boolean addNode(Node Node, boolean overwriteExisting){
        Node current = this.nodes.get(Node.getLabel());
        if(current != null){
            if(!overwriteExisting){
                return false;
            }
        }

        nodes.put(Node.getLabel(), Node);
        return true;
    }

    /**
     *
     * @param subCaseType The label of the Node to remove
     * @return Node The removed Node object
     */
    public Node removeNode(SubCaseType subCaseType){
        Node node = nodes.remove(subCaseType);

        return node;
    }

    /**
     *
     * @return Set<String> The unique labels of the Graph's Node objects
     */
    public Set<SubCaseType> NodeKeys(){
        return this.nodes.keySet();
    }
    public Collection<Node> getNodes(){
        return this.nodes.values();
    }

}
