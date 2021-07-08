

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Test-Klasse AppointmentTest.
 *
 * @author  (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class AppointmentTest
{
    private Appointment appointm1;
    private Appointment appointm2;
    private Appointment appointm3;
    private Appointment appointm4;
    private Appointment appointm5;


    /**
     * Konstruktor fuer die Test-Klasse AppointmentTest
     */
    public AppointmentTest()
    {
    }

    /**
     *  Setzt das Testgerüst fuer den Test.
     *
     * Wird vor jeder Testfall-Methode aufgerufen.
     */
    @Before
    public void setUp()
    {
        appointm1 = new Appointment("sdsd", 23);
        appointm2 = new Appointment("sdsd", 23);
        appointm3 = new Appointment("sdsd", 23);
        appointm4 = new Appointment("sdsd", 23);
        appointm5 = new Appointment("sdsd", 23);
    }

    /**
     * Gibt das Testgerüst wieder frei.
     *
     * Wird nach jeder Testfall-Methode aufgerufen.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void initializedDescription()
    {
        Appointment appointm2 = new Appointment("Hello World", 9);
        assertEquals("Hello World", appointm2.getDescription());
    }
    @Test
    public void initializedDuration()
    {
        Appointment appointm2 = new Appointment("Hello World", 9);
        assertEquals(9, appointm2.getDuration());
    }    

    @Test
    public void ZeroDuration()
    {
        Appointment appointm2 = new Appointment("Test", 0);
        assertEquals(false, appointm2.getDuration());
    }


    @Test
    public void ZeroDescription()
    {
        Appointment appointm2 = new Appointment("", 2);
        assertEquals(false, appointm2.getDescription());
    }
}





