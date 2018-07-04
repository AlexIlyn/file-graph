package ru.sbrf.uvz.graph;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

public class NodeTest {

    Graph graphMock = mock(Graph.class);

    @Test(expected = IllegalArgumentException.class)
    public void addDependent_ThrowIllegalAgrgumentException_SameKey() {
        Node nodeParent = new Node(SubCaseType.DRPA_CUST);
        Node nodeChild = new Node(SubCaseType.DRPA_CUST);
        nodeParent.addDependent(nodeChild);
//        assertThrows(IllegalArgumentException.class,
//                () -> nodeParent.addDependent(nodeChild));


    }


    @Test
    public void addDependent_ValidEdge() {
        Node nodeParent = new Node(SubCaseType.DRPA_CUST);
        Node nodeChild = new Node(SubCaseType.SAPBO_DOCS);
        nodeParent.setGraph(graphMock);
        nodeChild.setGraph(graphMock);
        nodeParent.addDependent(nodeChild);
        assertEquals(nodeParent, nodeParent.getNeighbors().get(0).getStart());
        assertEquals(nodeChild, nodeParent.getNeighbors().get(0).getEnd());
        assertEquals(nodeParent, nodeChild.getNeighbors().get(0).getStart());
        assertEquals(nodeChild, nodeChild.getNeighbors().get(0).getEnd());
    }

    @Test
    public void equals_ReturnFalse() {
        Node nodeOne = new Node(SubCaseType.DRPA_CUST);
        Node nodeTwo = new Node(SubCaseType.SAPBO_DOCS);
        assertFalse(nodeOne.equals(nodeTwo));
    }

    @Test
    public void equals_ReturnTrue() {
        Node nodeOne = new Node(SubCaseType.DRPA_CUST);
        Node nodeTwo = new Node(SubCaseType.DRPA_CUST);
        assertTrue(nodeOne.equals(nodeTwo));
    }
//
//    @Test
//    public void getNeighbors() {
//        Node nodeA = new Node(SubCaseType.DRPA_CUST);
//        nodeA.setGraph(graphMock);
//        Node nodeB = new Node(SubCaseType.DRPA_DOCS);
//        nodeB.setGraph(graphMock);
//
//        nodeA.addDependent(nodeB);
//        assertEquals(nodeA.getNeighbors().get(0), nodeB.getNeighbors().get(0));
//    }
}