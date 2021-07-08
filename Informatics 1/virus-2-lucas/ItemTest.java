

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ItemTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ItemTest
{
    /**
     * Default constructor for test class ItemTest
     */
    public ItemTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void TestGetNameFromItem()
    {
        Item item1 = new Item("Paper", "wow this is smart...", "blue", 451);
        assertEquals("Paper", item1.getName());
    }
    
    @Test
    public void TestGetDescriptionFromItem()
    {
        Item item1 = new Item("Paper", "wow this is smart...", "blue", 451);
        assertEquals("wow this is smart...", item1.getDescription());
    }
    
    @Test
    public void TestGetColourFromItem()
    {
        Item item1 = new Item("Paper", "wow this is smart...", "blue", 451);
        assertEquals("blue", item1.getColour());
    }
    
    @Test
    public void TestGetWeightFromItem()
    {
        Item item1 = new Item("Paper", "wow this is smart...", "blue", 451);
        assertEquals(451, item1.getWeight());
    }
}

