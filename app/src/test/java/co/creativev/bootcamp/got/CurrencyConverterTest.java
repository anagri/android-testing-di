package co.creativev.bootcamp.got;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CurrencyConverterTest {
    @Test
    public void testConvertsFrom1INRTo200IDR() throws Exception {
        CurrencyConverter converter = new CurrencyConverter();
        double idr = converter.convert(1, "INR");
        assertThat(idr, is(200d));
    }

    @Test
    public void testConverts100INRTo20000IDR() throws Exception {
        CurrencyConverter converter = new CurrencyConverter();
        double idr = converter.convert(100, "INR");
        assertThat(idr, is(20000d));
    }

    @Test
    public void testConverts200IDRTo1INR() throws Exception {
        CurrencyConverter converter = new CurrencyConverter();
        double inr = converter.convert(200, "IDR");
        assertThat(inr, is(1d));
    }

    @Test
    public void testConverts100IDRTo0_5INR() throws Exception {
        CurrencyConverter converter = new CurrencyConverter();
        double inr = converter.convert(100, "IDR");
        assertThat(inr, is(0.5d));
    }
}