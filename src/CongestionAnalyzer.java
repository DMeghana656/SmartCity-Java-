public class CongestionAnalyzer {

    public static String analyze(int vehicleCount) {

        if (vehicleCount <= 50) {
            return "LOW";
        }
        else if (vehicleCount <= 100) {
            return "MEDIUM";
        }
        else {
            return "HIGH";
        }
    }
}