package test;

import static org.junit.jupiter.api.Assertions.*;

import main.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for doing the JUnit tests for the Decorator.
 */
public class TestDecorator {

    HotDrink coffee1;
    HotDrink tea1;

    @BeforeEach
    void setUp() throws Exception {
        coffee1 = new Coffee();
        tea1 = new Tea();
    }

    @Test
    void testCoffee() {
        assertEquals(60, coffee1.getPrice());
    }

    @Test
    void testTea() {
        assertEquals(50, tea1.getPrice());
    }

    @Test
    void testCoffeeSugar() {
        HotDrink coffeeSugar1 = new AddSugarDecorator(coffee1);
        assertEquals(65, coffeeSugar1.getPrice());
    }

    @Test
    void testTeaSugar() {
        HotDrink teaSugar1 = new AddSugarDecorator(tea1);
        assertEquals(55, teaSugar1.getPrice());
    }

    @Test
    void testCoffeeCream() {
        HotDrink coffeeCream1 = new AddCreamDecorator(coffee1);
        assertEquals(70, coffeeCream1.getPrice());
    }

    @Test
    void testTeaCream() {
        HotDrink teaCream1 = new AddCreamDecorator(tea1);
        assertEquals(60, teaCream1.getPrice());
    }

    @Test
    void testCoffeeCreamSugar() {
        HotDrink coffeeSugar1 = new AddSugarDecorator(coffee1);
        HotDrink coffeeCreamSugar1 = new AddCreamDecorator(coffeeSugar1);
        assertEquals(75, coffeeCreamSugar1.getPrice());
    }

    @Test
    void testTeaCreamSugar() {
        HotDrink teaSugar1 = new AddSugarDecorator(tea1);
        HotDrink teaCreamSugar1 = new AddCreamDecorator(teaSugar1);
        assertEquals(65, teaCreamSugar1.getPrice());
    }

    @Test
    void testCoffeeCreamCreamSugar() {
        HotDrink coffeeSugar1 = new AddSugarDecorator(coffee1);
        HotDrink coffeeCreamSugar1 = new AddCreamDecorator(coffeeSugar1);
        HotDrink coffeeCreamCreamSugar1 = new AddCreamDecorator(coffeeCreamSugar1);
        assertEquals(85, coffeeCreamCreamSugar1.getPrice());
    }

    @Test
    void testTeaCreamCreamSugar() {
        HotDrink teaSugar1 = new AddSugarDecorator(tea1);
        HotDrink teaCreamSugar1 = new AddCreamDecorator(teaSugar1);
        HotDrink teaCreamCreamSugar1 = new AddCreamDecorator(teaCreamSugar1);
        assertEquals(75, teaCreamCreamSugar1.getPrice());
    }

}
