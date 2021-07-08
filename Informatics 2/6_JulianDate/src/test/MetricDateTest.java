package test;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import main.MetricDate;

class MetricDateTest {

    MetricDate md = new MetricDate();

    @Test
    @DisplayName("Test if converting Julian date to Metric date works with a small number")
    void ConvertJulianToMetricDateWithSmallNumber() {
        Assert.assertEquals("Converting Julian date to Metric date.", "1.6.1.2299", md.julianToMetricDate(2299160.5));
    }

    @Test
    @DisplayName("Test if converting Julian date to Metric date works with a large number")
    void ConvertJulianToMetricDateWithLargeNumber() {
        Assert.assertEquals("Converting Julian date to Metric date.", "1.2.8.993468", md.julianToMetricDate(993468829));
    }

    @Test
    @DisplayName("Test if converting Metric date to Julian date works with a small number")
    void ConvertMetricToJulianDateWithSmallNumber() {
        Assert.assertEquals("Converting Metric date to Julian date.", 2299161, md.metricToJulianDate(1, 6 , 1, 2299));
    }

    @Test
    @DisplayName("Test if converting Metric date to Julian date works with a large number")
    void ConvertMetricToJulianDateWithLargeNumber() {
        Assert.assertEquals("Converting Metric date to Julian date.", 10000500, md.metricToJulianDate(100, 50 , 9, 9999));
    }
}
