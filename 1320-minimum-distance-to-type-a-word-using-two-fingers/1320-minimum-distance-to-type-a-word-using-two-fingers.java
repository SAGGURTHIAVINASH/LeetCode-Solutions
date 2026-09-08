class Solution {
    public int minimumDistance(String word) {
        int INF = 1000000;

        int[][] dp = new int[27][27];

        for (int i = 0; i < 27; i++) {
            java.util.Arrays.fill(dp[i], INF);
        }

        dp[26][26] = 0;

        for (int i = 0; i < word.length(); i++) {
            int cur = word.charAt(i) - 'A';

            int[][] next = new int[27][27];

            for (int j = 0; j < 27; j++) {
                java.util.Arrays.fill(next[j], INF);
            }

            for (int a = 0; a < 27; a++) {
                for (int b = 0; b < 27; b++) {
                    if (dp[a][b] == INF) continue;

                    next[cur][b] = Math.min(
                        next[cur][b],
                        dp[a][b] + dist(a, cur)
                    );

                    next[a][cur] = Math.min(
                        next[a][cur],
                        dp[a][b] + dist(b, cur)
                    );
                }
            }

            dp = next;
        }

        int ans = INF;

        for (int a = 0; a < 27; a++) {
            for (int b = 0; b < 27; b++) {
                ans = Math.min(ans, dp[a][b]);
            }
        }

        return ans;
    }

    private int dist(int a, int b) {
        if (a == 26 || b == 26) {
            return 0;
        }

        int x1 = a / 6;
        int y1 = a % 6;

        int x2 = b / 6;
        int y2 = b % 6;

        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
