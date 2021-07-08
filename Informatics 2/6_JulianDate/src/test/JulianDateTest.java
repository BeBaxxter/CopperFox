package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import main.JulianDate;

class JulianDateTest {

	private JulianDate refJulianDate1;
	private JulianDate refJulianDate2;

	@BeforeEach
	public void setUp() throws Exception {
		refJulianDate1 = new JulianDate(1582, 10, 15);
		refJulianDate2 = new JulianDate(1582, 10, 20);
	}

	@Test
	@DisplayName("Test if converting a date to Julian date works correctly")
	void testConvertDateToJulianDate() {
		Assert.assertEquals(2299160.5, refJulianDate1.julianDayNumber, 0.0);
	}

	@Test
	@DisplayName("Test if the daysBetween method works correctly")
	void testDaysBetweenTwoDate() {
		Assert.assertEquals(5, refJulianDate1.daysBetween(refJulianDate2), 0.0);
	}

    @Test
    @DisplayName("Test if the isAfter method works correctly")
    void testisAfter() {
        Assert.assertFalse(refJulianDate1.isAfter(refJulianDate2));
        Assert.assertTrue(refJulianDate2.isAfter(refJulianDate1));
    }

	@Test
	@DisplayName("Test if the today method works correctly")
	void testToday() {
	Assert.assertNotNull(refJulianDate1.today());
	}

	@Test
	@DisplayName("Test if the tomorrow method works correctly")
	void testTomorrow() {
		Assert.assertNotNull(refJulianDate1.tomorrow());
	}

	@Test
	@DisplayName("Test if the yesterday method works correctly")
	void testYesterday() {
		Assert.assertNotNull(refJulianDate1.yesterday());
	}
}
