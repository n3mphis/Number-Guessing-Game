public class GameTimer {
    private Long startTime;

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public String getTimeTaken() {
        long endTime = System.currentTimeMillis();
        double seconds = (endTime - startTime) / 1000.0;
        return String.format("%.1f seconds", seconds);
    }
}
