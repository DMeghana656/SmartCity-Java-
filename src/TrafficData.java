public class TrafficData {

    private String roadName;
    private int vehicleCount;

    public TrafficData(String roadName, int vehicleCount) {
        this.roadName = roadName;
        this.vehicleCount = vehicleCount;
    }

    public String getRoadName() {
        return roadName;
    }

    public int getVehicleCount() {
        return vehicleCount;
    }

    public void displayData() {
        System.out.println(
            roadName + " : " + vehicleCount + " vehicles"
        );
    }
}