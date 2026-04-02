package Project.Weekend;

public class InterestCalculator {

    public static double[] calculate(int money, Product p) {
        double[] interests = new double[p.period];
        double rate = p.baseRate / 100;

        for (int j = 1; j <= p.period; j++) {
            double interest = money * (rate / 12) * j;
            interests[j - 1] = interest;
        }

        return interests;
    }
}
