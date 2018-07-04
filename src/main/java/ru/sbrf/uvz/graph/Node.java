package ru.sbrf.uvz.graph;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Node {

    private ArrayList<Edge> neighborhood;
    @Getter
    private final SubCaseType key;
    @Getter
    @Setter
    private Graph graph;

    /**
     * @param key The unique key associated with this Node
     */
    Node(SubCaseType key) {
        this.key = key;
        this.neighborhood = new ArrayList<>();
    }

    boolean isRoot() {
        for (Edge edge : neighborhood) {
            if (edge.getEnd() == this) return false;
        }
        return true;
    }

    List<Node> getChildNodes() {
        List<Node> result = new ArrayList<>();
        neighborhood.forEach(e -> {
            if (e.getStart() == this) result.add(e.getEnd());
        });
        return result;
    }

    List<Node> getParentNodes() {
        List<Node> result = new ArrayList<>();
        neighborhood.forEach(e -> {
            if (e.getEnd() == this) result.add(e.getStart());
        });
        return result;
    }

    /**
     * This method adds an Edge to the incidence neighborhood of this graph iff
     * the edge is not already present.
     *
     * @param child The node to add as dependent
     */
    Node addDependent(Node child) {
        if (child.getKey().equals(key)) throw new IllegalArgumentException("Node cant have dependent with same key");
        //if(child.getGraph() != this.getGraph()) throw new IllegalArgumentException("Dependent cant belong to different graph");
        for (Edge edge : neighborhood) {
            if (edge.getEnd().equals(child)) return edge.getEnd();
        }
        graph.addNode(child);
        Edge edge = new Edge(this, child);
        this.addEdge(edge);
        child.addEdge(edge);
        return child;
    }

    private void addEdge(Edge edge) {
        this.neighborhood.add(edge);

    }

    /**
     * @return String A String representation of this Node
     */
    @Override
    public String toString() {
        return "[NODE: " + key.name() + "]";
    }

    /**
     * @return ArrayList<Edge> A copy of this.neighborhood. Modifying the returned
     * ArrayList will not affect the neighborhood of this Node
     */
    ArrayList<Edge> getNeighbors() {
        return new ArrayList<>(this.neighborhood);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return key == node.key &&
                Objects.equals(graph, node.graph);
    }

    @Override
    public int hashCode() {

        return Objects.hash(key, graph);
    }
}
