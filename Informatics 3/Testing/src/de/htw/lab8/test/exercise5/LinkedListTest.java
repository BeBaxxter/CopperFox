package de.htw.lab8.test.exercise5;

import static org.junit.Assert.assertEquals;
import de.htw.lab8.main.exercise5.LinkedList;
import org.junit.jupiter.api.*;

class LinkedListTest {

    private static LinkedList testList = new LinkedList();

    @BeforeEach
    void init() {
        testList = new LinkedList();
    }

    @Test
    void testPushOneToLinkedList() {
        testList.push("Artus");
        assertEquals("Artus", testList.toString());
    }

    @Test
    void testPushMultipleToLinkedList() {
        testList.push("Artus");
        testList.push("Robin");
        testList.push("Max");
        testList.push("Lucas");
        assertEquals("Artus-Robin-Max-Lucas", testList.toString());
    }

    @Test
    void testPopOneFromLinkedList() {
        testList.push("Artus");
        testList.pop();
        assertEquals("", testList.toString());
    }

    @Test
    void testPopMultipleFromLinkedList() {
        testList.push("Artus");
        testList.push("Robin");
        testList.push("Max");
        testList.push("Lucas");
        testList.pop();
        testList.pop();
        assertEquals("Artus-Robin", testList.toString());
    }

    @Test
    void testReverseLinkedList() {
        testList.push("Artus");
        testList.push("Robin");
        testList.push("Max");
        testList.push("Lucas");
        testList.reverse();
        assertEquals("Lucas-Max-Robin-Artus", testList.toString());
    }

    @Test
    void testToString() {
        testList.push("Max");
        testList.push("Lucas");
        assertEquals("Max-Lucas", testList.toString());
    }

    @Test
    void testToStringEmpty() {
        assertEquals("", testList.toString());
    }
}