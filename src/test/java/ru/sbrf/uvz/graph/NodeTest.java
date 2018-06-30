package ru.sbrf.uvz.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NodeTest {

    @Test
    void getGraph() {
    }

    @Test
    void getKey() {
        Node node = new Node(SubCaseType.DRPA_CUST);
        assertEquals(SubCaseType.DRPA_CUST, node.getKey());
    }

    @Test
    void addDependent() {
    }

    @Test
    void equals() {
    }

    @Test
    void getNeighbors() {
    }
}