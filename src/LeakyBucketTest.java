public class LeakyBucketTest {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" LEAKY BUCKET TEST");
        System.out.println("=================================");

        LeakyBucket bucket = new LeakyBucket(10);

        System.out.println(
                "\nBucket Capacity: "
                + bucket.getCapacity()
                + " packets"
        );

        System.out.println("\nADDING 15 PACKETS");
        System.out.println("---------------------------------");

        for (int i = 1; i <= 15; i++) {

            Packet packet = new Packet(
                    i,
                    "Traffic Network",
                    "Traffic Management Center",
                    "TCP",
                    1024
            );

            bucket.addPacket(packet);
        }

        System.out.println(
                "\nPackets currently in bucket: "
                + bucket.getCurrentPackets()
        );

        System.out.println("\nTRANSMITTING PACKETS");
        System.out.println("---------------------------------");

        for (int i = 1; i <= 3; i++) {
            bucket.transmitPacket();
        }

        System.out.println(
                "\nPackets remaining in bucket: "
                + bucket.getCurrentPackets()
        );

        bucket.displayStatistics();

        System.out.println("\n=================================");
        System.out.println(" LEAKY BUCKET TEST COMPLETE");
        System.out.println("=================================");
    }
}
