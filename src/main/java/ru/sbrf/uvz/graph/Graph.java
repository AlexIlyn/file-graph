package ru.sbrf.uvz.graph;

import java.util.*;

public class Graph {

    private HashMap<String, Node> vertices;
    private HashMap<Integer, Edge> edges;

    public Graph(){
        this.vertices = new HashMap<String, Node>();
        this.edges = new HashMap<Integer, Edge>();
    }



    List<SubCaseType> getLinkedSubCaseTypes(LinkedSubCaseType subCases) {

        return null;
    }

    List<SubCaseType> getSingleSubCaseTypes(LinkedSubCaseType subCases) {

        return null;
    }

    /**
     * This constructor accepts an ArrayList<Node> and populates
     * this.vertices. If multiple Node objects have the same label,
     * then the last Node with the given label is used.
     *
     * @param vertices The initial Vertices to populate this Graph
     */
    public Graph(ArrayList<Node> vertices){
        this.vertices = new HashMap<String, Node>();
        this.edges = new HashMap<Integer, Edge>();

        for(Node v: vertices){
            this.vertices.put(v.getLabel(), v);
        }

    }

    /**
     * This method adds am edge between Vertices one and two
     * of weight 1, if no Edge between these Vertices already
     * exists in the Graph.
     *
     * @param one The first Node to add
     * @param two The second Node to add
     * @return true iff no Edge relating one and two exists in the Graph
     */
    public boolean addEdge(Node one, Node two){
        return addEdge(one, two, 1);
    }


    /**
     * Accepts two vertices and a weight, and adds the edge
     * ({one, two}, weight) iff no Edge relating one and two
     * exists in the Graph.
     *
     * @param one The first Node of the Edge
     * @param two The second Node of the Edge
     * @param weight The weight of the Edge
     * @return true iff no Edge already exists in the Graph
     */
    public boolean addEdge(Node one, Node two, int weight){
        if(one.equals(two)){
            return false;
        }

        //ensures the Edge is not in the Graph
        Edge e = new Edge(one, two, weight);
        if(edges.containsKey(e.hashCode())){
            return false;
        }

        //and that the Edge isn't already incident to one of the vertices
        else if(one.containsNeighbor(e) || two.containsNeighbor(e)){
            return false;
        }

        edges.put(e.hashCode(), e);
        one.addNeighbor(e);
        two.addNeighbor(e);
        return true;
    }

    /**
     *
     * @param e The Edge to look up
     * @return true iff this Graph contains the Edge e
     */
    public boolean containsEdge(Edge e){
        if(e.getStart() == null || e.getEnd() == null){
            return false;
        }

        return this.edges.containsKey(e.hashCode());
    }


    /**
     * This method removes the specified Edge from the Graph,
     * including as each Node's incidence neighborhood.
     *
     * @param e The Edge to remove from the Graph
     * @return Edge The Edge removed from the Graph
     */
    public Edge removeEdge(Edge e){
        e.getStart().removeNeighbor(e);
        e.getEnd().removeNeighbor(e);
        return this.edges.remove(e.hashCode());
    }

    /**
     *
     * @param Node The Node to look up
     * @return true iff this Graph contains Node
     */
    public boolean containsNode(Node Node){
        return this.vertices.get(Node.getLabel()) != null;
    }

    /**
     *
     * @param label The specified Node label
     * @return Node The Node with the specified label
     */
    public Node getNode(String label){
        return vertices.get(label);
    }

    /**
     * This method adds a Node to the graph. If a Node with the same label
     * as the parameter exists in the Graph, the existing Node is overwritten
     * only if overwriteExisting is true. If the existing Node is overwritten,
     * the Edges incident to it are all removed from the Graph.
     *
     * @param Node
     * @param overwriteExisting
     * @return true iff Node was added to the Graph
     */
    public boolean addNode(Node Node, boolean overwriteExisting){
        Node current = this.vertices.get(Node.getLabel());
        if(current != null){
            if(!overwriteExisting){
                return false;
            }

            while(current.getNeighborCount() > 0){
                this.removeEdge(current.getNeighbor(0));
            }
        }


        vertices.put(Node.getLabel(), Node);
        return true;
    }

    /**
     *
     * @param label The label of the Node to remove
     * @return Node The removed Node object
     */
    public Node removeNode(String label){
        Node v = vertices.remove(label);

        while(v.getNeighborCount() > 0){
            this.removeEdge(v.getNeighbor((0)));
        }

        return v;
    }

    /**
     *
     * @return Set<String> The unique labels of the Graph's Node objects
     */
    public Set<String> NodeKeys(){
        return this.vertices.keySet();
    }

    /**
     *
     * @return Set<Edge> The Edges of this graph
     */
    public Set<Edge> getEdges(){
        return new HashSet<Edge>(this.edges.values());
    }

}
