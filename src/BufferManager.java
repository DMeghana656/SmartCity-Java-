import java.util.LinkedList;
import java.util.Queue;

public class BufferManager {

    private int capacity;
    private Queue<Packet> buffer;

    private int totalAdded;
    private int totalRemoved;
    private int totalRejected;

    public BufferManager(int capacity) {

        this.capacity = capacity;
        this.buffer = new LinkedList<>();

        this.totalAdded = 0;
        this.totalRemoved = 0;
        this.totalRejected = 0;
    }

    // Add packet to buffer
    public boolean addPacket(Packet packet) {

        if (buffer.size() < capacity) {

            buffer.add(packet);
            totalAdded++;

            System.out.println(
                    "Packet " + packet.getPacketId()
                    + " added to buffer."
            );

            return true;
        }

        totalRejected++;

        System.out.println(
                "Packet " + packet.getPacketId()
                + " REJECTED - Buffer Full"
        );

        return false;
    }

    // Remove one packet from buffer
    public Packet removePacket() {

        if (!buffer.isEmpty()) {

            Packet packet = buffer.poll();
            totalRemoved++;

            System.out.println(
                    "Packet " + packet.getPacketId()
                    + " removed from buffer."
            );

            return packet;
        }

        System.out.println("Buffer is empty.");

        return null;
    }

    // Check current buffer size
    public int getCurrentSize() {
        return buffer.size();
    }

    // Get buffer capacity
    public int getCapacity() {
        return capacity;
    }

    // Get total packets successfully added
    public int getTotalAdded() {
        return totalAdded;
    }

    // Get total packets removed
    public int getTotalRemoved() {
        return totalRemoved;
    }

    // Get total packets rejected
    public int getTotalRejected() {
        return totalRejected;
    }

    // Calculate buffer utilization
    public double getBufferUtilization() {

        if (capacity == 0) {
            return 0.0;
        }

        return ((double) buffer.size() / capacity) * 100;
    }

    // Display buffer statistics
    public void displayStatistics() {

        System.out.println("\nBUFFER MANAGEMENT STATISTICS");
        System.out.println("---------------------------------");

        System.out.println(
                "Buffer Capacity    : " + capacity
        );

        System.out.println(
                "Current Buffer Size : " + buffer.size()
        );

        System.out.println(
                "Packets Added       : " + totalAdded
        );

        System.out.println(
                "Packets Removed     : " + totalRemoved
        );

        System.out.println(
                "Packets Rejected    : " + totalRejected
        );

        System.out.printf(
                "Buffer Utilization  : %.2f%%%n",
                getBufferUtilization()
        );
    }
}
