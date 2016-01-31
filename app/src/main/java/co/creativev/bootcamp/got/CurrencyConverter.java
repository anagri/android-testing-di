package co.creativev.bootcamp.got;

public class CurrencyConverter {
    public double convert(int amount, String currency) {
        if (currency.equals("INR"))
            return amount * 200.0;
        return amount / 200.0;
    }
}
