package ru.sbrf.uvz.graph;

import lombok.Getter;
import lombok.Setter;
import lombok.var;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {

    private ArrayList<Edge> neighborhood;
    private @Getter
    final SubCaseType key;
    private @Getter
    @Setter
    Graph graph;

    /**
     * @param key The unique key associated with this Node
     */
    public Node(SubCaseType key) {
        this.key = key;
        this.neighborhood = new ArrayList<>();
    }

    public boolean isRoot() {
        for (var edge : neighborhood) {
            if (edge.getEnd() == this) return false;
        }
        return true;
    }

    public List<Node> getChildNodes() {
        List<Node> result = new ArrayList<>();
        neighborhood.forEach(e -> {
            if (e.getStart() == this) result.add(e.getEnd());
        });
        return result;
    }

    /**
     * This method adds an Edge to the incidence neighborhood of this graph iff
     * the edge is not already present.
     *
     * @param child The node to add as dependent
     */
    public Node addDependent(Node child) {
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
    public ArrayList<Edge> getNeighbors() {
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
