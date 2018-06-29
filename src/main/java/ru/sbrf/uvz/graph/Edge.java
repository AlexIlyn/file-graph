package ru.sbrf.uvz.graph;


public class Edge {

    private Node start, end;

    /**
     *
     * @param start The first vertex in the Edge
     * @param end The second vertex of the Edge
     */
    public Edge(Node start, Node end){
        this.start = (start.getLabel().compareTo(end.getLabel()) <= 0) ? start : end;
        this.end = (this.start == start) ? end : start;
    }


    /**
     *
     * @param current
     * @return The neighbor of current along this Edge
     */
    public Node getNeighbor(Node current){
        if(!(current.equals(start) || current.equals(end))){
            return null;
        }

        return (current.equals(start)) ? end : start;
    }

    /**
     *
     * @return Node this.start
     */
    public Node getStart(){
        return this.start;
    }

    /**
     *
     * @return Node this.end
     */
    public Node getEnd(){
        return this.end;
    }

    /**
     *
     * @return String A String representation of this Edge
     */
    public String toString(){
        return "(" + start + ", " + end + ")";
    }

    /**
     *
     * @return int The hash code for this Edge 
     */
    public int hashCode(){
        return (start.getLabel().toString() + end.getLabel().toString()).hashCode();
    }

    /**
     *
     * @param other The Object to compare against this
     * @return ture iff other is an Edge with the same Vertices as this
     */
    public boolean equals(Object other){
        if(!(other instanceof Edge)){
            return false;
        }

        Edge e = (Edge)other;

        return e.start.equals(this.start) && e.end.equals(this.end);
    }
}
