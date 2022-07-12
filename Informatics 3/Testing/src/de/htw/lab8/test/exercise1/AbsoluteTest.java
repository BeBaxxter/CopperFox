package de.htw.lab8.test.exercise1;

import de.htw.lab8.main.exercise1.Absolute;
import de.htw.lab8.main.exercise1.AbsoluteEquivalence;
import org.junit.jupiter.api.*;

import static org.junit.Assert.assertEquals;

public class AbsoluteTest {

    private static Absolute absolute;
    AbsoluteEquivalence absoluteEquivalence = new AbsoluteEquivalence();

    @BeforeAll
    static void setUp() {
        absolute = new Absolute();
    }

    @Test
    void testPositiveValueOf() {
        assertEquals(1, absolute.absoluteValueOf(absoluteEquivalence.positiveValue));
    }

    @Test
    void testNegativeValueOf() {
        assertEquals(1, absolute.absoluteValueOf(absoluteEquivalence.negativeValue));
    }

    @Test
    void testZeroValueOf() {
        assertEquals(0, absolute.absoluteValueOf(absoluteEquivalence.zeroValue));
    }

    @Test
    void testBigValueOf() {
        assertEquals(1000000000, absolute.absoluteValueOf(absoluteEquivalence.bigValue));
    }

}