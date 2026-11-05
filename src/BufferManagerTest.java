public class BufferManagerTest {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" BUFFER MANAGER TEST");
        System.out.println("=================================");

        BufferManager buffer = new BufferManager(5);

        System.out.println(
                "\nBuffer Capacity: "
                + buffer.getCapacity()
                + " packets"
        );

        System.out.println("\nADDING 8 PACKETS");
        System.out.println("---------------------------------");

        for (int i = 1; i <= 8; i++) {

            Packet packet = new Packet(
                    i,
                    "Traffic Network",
                    "Traffic Management Center",
                    "TCP",
                    1024
            );

            buffer.addPacket(packet);
        }

        System.out.println(
                "\nCurrent Buffer Size: "
                + buffer.getCurrentSize()
        );

        System.out.println("\nREMOVING 2 PACKETS");
        System.out.println("---------------------------------");

        buffer.removePacket();
        buffer.removePacket();

        System.out.println(
                "\nCurrent Buffer Size: "
                + buffer.getCurrentSize()
        );

        buffer.displayStatistics();

        System.out.println("\n=================================");
        System.out.println(" BUFFER MANAGER TEST COMPLETE");
        System.out.println("=================================");
    }
}
