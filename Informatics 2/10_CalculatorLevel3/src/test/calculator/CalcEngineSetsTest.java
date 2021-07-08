package test.calculator;

import main.calculator.sets.CalcEngineSets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CalcEngineSetsTest {

    CalcEngineSets engine;
    Set<String> testValue1, testValue2, testValue3, testValue4;

    @BeforeEach
    public void setUp() {
        engine = new CalcEngineSets();
        testValue1 = new HashSet<>();
        testValue2 = new HashSet<>();
        testValue3 = new HashSet<>();
        testValue4 = new HashSet<>();

        testValue1.add("A");
        testValue1.add("B");
        testValue1.add("C");
        testValue1.add("D");
        testValue1.add("E");
        testValue1.add("F");

        testValue2.add("1");
        testValue2.add("B");
        testValue2.add("D");
        testValue2.add("E");
        testValue2.add("F");
        testValue2.add("9");

        testValue3.add("6");
        testValue4.add("4");

    }

    @Test
    @DisplayName("Test if parsing a string to set works with given pattern")
    void parseStringToSet(){

        Set<String> compare = Collections.singleton("A, 1, !, B, C, D");
        Set<String> result = engine.parseStringToSet("A,B,C,D,1,!");

        assertEquals(compare.toString(), result.toString());
    }

    @Test
    @DisplayName("Test if clear stack works correctly")
    void clear() {

        Set<String> setResult = new HashSet<>();
        setResult.add("1");
        setResult.add("2");
        setResult.clear();

        assertEquals("[]", setResult.toString());
    }

    @Test
    @DisplayName("Test if merging 2 test values together")
    void union() {
        Set<String> setResult = new HashSet<>();
        setResult.addAll(testValue1);
        setResult.addAll(testValue2);

        assertEquals("[A, 1, B, C, D, E, F, 9]", setResult.toString());
    }

    @Test
    @DisplayName("Test if the intersection of 2 values works correctly")
    void intersection() {

        Set<String> result = new HashSet<>();

        for (String element : testValue1){
            if (testValue2.contains(element)){
                result.add(element);
            }
        }

        assertEquals("[B, D, E, F]", result.toString());
    }

    @Test
    @DisplayName("Test if the subtraction of 2 values works correctly")
    void subtraction() {

        Set<String> result = new HashSet<>(testValue1);

        for (String element : testValue2){
            result.remove(element);
        }

        assertEquals("[A, C]", result.toString());
    }

    @Test
    @DisplayName("Test if push a set a to b works correctly")
    void pushSetAtoB() {
        testValue3.addAll(testValue1);
        assertEquals("[A, B, C, D, E, 6, F]", testValue3.toString());
    }

    @Test
    @DisplayName("Test if push a set b to a works correctly")
    void pushSetBtoA() {
        testValue4.addAll(testValue1);
        assertEquals("[A, B, C, 4, D, E, F]", testValue4.toString());
    }

    @Test
    @DisplayName("Test if length a is displayed correctly")
    void lengthA() {
        assertEquals(6, testValue1.size());
    }

    @Test
    @DisplayName("Test if length b is displayed correctly")
    void lengthB() {
        assertEquals(1, testValue4.size());
    }

}