package backend.academy.samples;

class CoinToss {
    public static void main(String[] args) {
        int n = 7; // количество бросков
        double[][] dp = new double[n + 1][4];

        dp[0][0] = 1.0;

        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] * 0.5;
            dp[i][1] = dp[i - 1][0] * 0.5 + dp[i - 1][1] * 0.5;
            dp[i][2] = dp[i - 1][1] * 0.5 + dp[i - 1][2] * 0.5;
            dp[i][3] = dp[i - 1][2] * 0.5 + dp[i - 1][3] * 0.5;
        }

        double probability = dp[n][3];
        double result = probability * 64;

        System.out.println("Result: " + result);
    }
}
