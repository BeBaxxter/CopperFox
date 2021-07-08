package test;

import static org.junit.jupiter.api.Assertions.*;

import main.Stack;
import main.StackAsList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StackAsListTest {

    @Test
    @DisplayName("Test push and pop [1]")
    void testPushAndPopCase_1(){
        Stack s = new StackAsList();
        s.push("Hallo");
        s.push("Welt");
        assertEquals("Welt", s.pop());
    }

    @Test
    @DisplayName("Test push and pop [2]")
    void testPushAndPopCase_2(){
        Stack s = new StackAsList();
        s.push("Hallo");
        s.push("Welt");
        s.push("Berlin");
        s.push("Sonne");
        assertEquals("Sonne", s.pop());
    }

    @Test
    @DisplayName("Test push and pop [3]")
    void testPushAndPopCase_3(){
        Stack s = new StackAsList();
        s.push("Hallo");
        s.push("Welt");
        s.push("Berlin");
        s.push("Sonne");
        s.pop();
        assertEquals("Berlin", s.pop());
    }

    @Test
    @DisplayName("Test push and pop [4]")
    void testToString() {
        Stack s = new StackAsList();
        s.push("Hallo");
        assertEquals("Hallo", s.pop().toString());
    }

}