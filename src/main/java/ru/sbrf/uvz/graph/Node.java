package ru.sbrf.uvz.graph;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@EqualsAndHashCode
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
        this.neighborhood = new ArrayList<Edge>();
    }

    /**
     * This method adds an Edge to the incidence neighborhood of this graph iff
     * the edge is not already present.
     * @param node The node to add as dependent
     */
    public Node addDependent(Node node) {
        for (Edge edge : neighborhood) {
            if (edge.getEnd().equals(node)) return edge.getEnd();
        }
        Edge edge = new Edge(this, node);
        this.neighborhood.add(edge);
        graph.addNode(node);
        return node;
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

}
