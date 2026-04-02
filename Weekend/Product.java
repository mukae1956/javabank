package Project.Weekend;

public class Product {
    String bankName;
    String name;
    int period;
    double baseRate;
    double maxRate;

    public Product(String bankName, String name, int period, double baseRate, double maxRate) {
        this.bankName = bankName;
        this.name = name;
        this.period = period;
        this.baseRate = baseRate;
        this.maxRate = maxRate;
    }
}
