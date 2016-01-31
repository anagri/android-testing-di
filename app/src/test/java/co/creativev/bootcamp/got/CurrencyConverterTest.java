package co.creativev.bootcamp.got;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CurrencyConverterTest {
    @Test
    public void testConvertsFrom1INRTo200IDR() throws Exception {
        CurrencyConverter converter = new CurrencyConverter();
        double idr = converter.convert(1, "INR");
        assertEquals(0.001, 200d, idr);
    }

    @Test
    public void testConverts100INRTo20000IDR() throws Exception {
        CurrencyConverter converter = new CurrencyConverter();
        double idr = converter.convert(100, "INR");
        assertEquals(0.001, 20000d, idr);
    }

    @Test
    public void testConverts200IDRTo1INR() throws Exception {
        CurrencyConverter converter = new CurrencyConverter();
        double inr = converter.convert(200, "IDR");
        assertEquals(0.001, 1, inr);
    }

    @Test
    public void testConverts100IDRTo0_5INR() throws Exception {
        CurrencyConverter converter = new CurrencyConverter();
        double inr = converter.convert(100, "INR");
        assertEquals(0.001, 0.5, inr);
    }
}