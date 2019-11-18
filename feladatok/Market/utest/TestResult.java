package utest;

public class TestResult {
    public final int score;
    public final String reason;
    public final String apiError;

    TestResult(int score, String reason, String apiError) {
        this.score    = score;
        this.reason   = reason;
        this.apiError = apiError;
    }
}
