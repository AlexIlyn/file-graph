package ru.sbrf.uvz.graph;

import java.util.ArrayList;

public class Node {

    private ArrayList<Edge> neighborhood;
    private SubCaseType subCaseType;
    private Graph graph;

    public Graph getGraph() {
        return graph;
    }

    public SubCaseType getSubCaseType() {
        return subCaseType;
    }

    public void setSubCaseType(SubCaseType subCaseType) {
        this.subCaseType = subCaseType;
    }

    /**
     *
     * @param subCaseType The unique label associated with this Node
     */
    public Node(Graph graph, SubCaseType subCaseType){
        this.subCaseType = subCaseType;
        this.graph = graph;
        this.neighborhood = new ArrayList<Edge>();
    }




    /**
     * This method adds an Edge to the incidence neighborhood of this graph iff
     * the edge is not already present.
     *
     * @param node The node to add as depandent
     */
    public Node addDependent(Node node){
        for (Edge edge : neighborhood){
            if(edge.getEnd().equals(node)) return edge.getEnd();
        }
        Edge edge = new Edge(this, node);
        this.neighborhood.add(edge);
        return node;
    }

    /**
     *
     * @param other The edge for which to search
     * @return true iff other is contained in this.neighborhood
     */
    public boolean containsNeighbor(Edge other){
        return this.neighborhood.contains(other);
    }

    /**
     *
     * @param index The index of the Edge to retrieve
     * @return Edge The Edge at the specified index in this.neighborhood
     */
    public Edge getNeighbor(int index){
        return this.neighborhood.get(index);
    }


    /**
     *
     * @param index The index of the edge to remove from this.neighborhood
     * @return Edge The removed Edge
     */
    Edge removeNeighbor(int index){
        return this.neighborhood.remove(index);
    }

    /**
     *
     * @param e The Edge to remove from this.neighborhood
     */
    public void removeNeighbor(Edge e){
        this.neighborhood.remove(e);
    }


    /**
     *
     * @return int The number of neighbors of this Node
     */
    public int getNeighborCount(){
        return this.neighborhood.size();
    }


    /**
     *
     * @return String The label of this Node
     */
    public SubCaseType getLabel(){
        return this.subCaseType;
    }


    /**
     *
     * @return String A String representation of this Node
     */
    public String toString(){
        return "Node " + subCaseType.name();
    }

    /**
     *
     * @return The hash code of this Node's label
     */
    public int hashCode(){
        return this.subCaseType.hashCode();
    }

    /**
     *
     * @param other The object to compare
     * @return true iff other instanceof Node and the two Node objects have the same label
     */
    public boolean equals(Object other){
        if(!(other instanceof Node)){
            return false;
        }

        Node node = (Node)other;
        return this.subCaseType.equals(node.subCaseType);
    }

    /**
     *
     * @return ArrayList<Edge> A copy of this.neighborhood. Modifying the returned
     * ArrayList will not affect the neighborhood of this Node
     */
    public ArrayList<Edge> getNeighbors(){
        return new ArrayList<Edge>(this.neighborhood);
    }

}
