package ru.sbrf.uvz.graph;


public class Edge implements Comparable<Edge> {

    private Node start, end;
    private int weight;

    /**
     *
     * @param start The first vertex in the Edge
     * @param end The second vertex in the Edge
     */
    public Edge(Node start, Node end){
        this(start, end, 1);
    }

    /**
     *
     * @param start The first vertex in the Edge
     * @param end The second vertex of the Edge
     * @param weight The weight of this Edge
     */
    public Edge(Node start, Node end, int weight){
        this.start = (start.getLabel().compareTo(end.getLabel()) <= 0) ? start : end;
        this.end = (this.start == start) ? end : start;
        this.weight = weight;
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
     * @return int The weight of this Edge
     */
    public int getWeight(){
        return this.weight;
    }


    /**
     *
     * @param weight The new weight of this Edge
     */
    public void setWeight(int weight){
        this.weight = weight;
    }


    /**
     * Note that the compareTo() method deviates from 
     * the specifications in the Comparable interface. A 
     * return value of 0 does not indicate that this.equals(other).
     * The equals() method checks the Node endpoints, while the 
     * compareTo() is used to compare Edge weights
     *
     * @param other The Edge to compare against this
     * @return int this.weight - other.weight
     */
    public int compareTo(Edge other){
        return this.weight - other.weight;
    }

    /**
     *
     * @return String A String representation of this Edge
     */
    public String toString(){
        return "({" + start + ", " + end + "}, " + weight + ")";
    }

    /**
     *
     * @return int The hash code for this Edge 
     */
    public int hashCode(){
        return (start.getLabel() + end.getLabel()).hashCode();
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
