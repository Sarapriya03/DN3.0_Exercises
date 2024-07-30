package FinancialForecastingExample;

public class FinancialForecasting {
    public static double predictFutureValue(double currentValue, double growthRate, int years) {
        // Base case: if no more years to predict, return the current value
        if (years == 0) {
            return currentValue;
        }
        // Recursive case: calculate the value for the next year
        double nextValue = currentValue * (1 + growthRate);
        return predictFutureValue(nextValue, growthRate, years - 1);
    }

    public static void main(String[] args) {
        double currentValue = 1000.0; // Initial value
        double growthRate = 0.05; // 5% growth rate
        int years = 5; // Number of years to predict

        double futureValue = predictFutureValue(currentValue, growthRate, years);
        System.out.println("Predicted Future Value after " + years + " years: " + futureValue);
    }

}
