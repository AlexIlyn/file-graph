package ru.sbrf.uvz.graph;

import lombok.Data;

@Data
public class Edge {

    private final Node start;
    private final Node end;

    /**
     * @param start The first node in the Edge
     * @param end The second vertex of the Edge
     */
    public Edge(Node start, Node end){
        this.start = start;
        this.end = end;
    }

    /**
     * @return String A String representation of this Edge
     */
    @Override
    public String toString(){
        return "[EDGE: (" + start + ", " + end + ")]";
    }
}
