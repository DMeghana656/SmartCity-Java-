public class LeakyBucket {

    private int capacity;
    private int currentPackets;
    private int totalPackets;
    private int transmittedPackets;
    private int droppedPackets;

    public LeakyBucket(int capacity) {

        this.capacity = capacity;
        this.currentPackets = 0;
        this.totalPackets = 0;
        this.transmittedPackets = 0;
        this.droppedPackets = 0;
    }

    public boolean addPacket(Packet packet) {

        totalPackets++;

        if (currentPackets < capacity) {

            currentPackets++;

            System.out.println(
                    "Packet " + packet.getPacketId()
                    + " added to bucket."
            );

            return true;
        }
        else {

            droppedPackets++;

            System.out.println(
                    "Packet " + packet.getPacketId()
                    + " DROPPED - Bucket Full"
            );

            return false;
        }
    }

    public void transmitPacket() {

        if (currentPackets > 0) {

            currentPackets--;
            transmittedPackets++;

            System.out.println(
                    "1 packet transmitted from bucket."
            );
        }
        else {

            System.out.println(
                    "Bucket is empty."
            );
        }
    }

    public int getCurrentPackets() {
        return currentPackets;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTotalPackets() {
        return totalPackets;
    }

    public int getTransmittedPackets() {
        return transmittedPackets;
    }

    public int getDroppedPackets() {
        return droppedPackets;
    }

    public double getDropRate() {

        if (totalPackets == 0) {
            return 0.0;
        }

        return ((double) droppedPackets / totalPackets) * 100;
    }

    public void displayStatistics() {

        System.out.println("\nLEAKY BUCKET STATISTICS");
        System.out.println("---------------------------------");

        System.out.println(
                "Bucket Capacity    : " + capacity
        );

        System.out.println(
                "Total Packets      : " + totalPackets
        );

        System.out.println(
                "Current Packets    : " + currentPackets
        );

        System.out.println(
                "Transmitted Packets: " + transmittedPackets
        );

        System.out.println(
                "Dropped Packets    : " + droppedPackets
        );

        System.out.printf(
                "Packet Drop Rate   : %.2f%%%n",
                getDropRate()
        );
    }
}
