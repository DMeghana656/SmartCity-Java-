public class PerformanceAnalyzerTest {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" PERFORMANCE ANALYZER TEST");
        System.out.println("=================================");

        int totalPackets = 20;
        int transmittedPackets = 10;
        int droppedPackets = 10;

        int packetSize = 1024;

        double timeSeconds = 5.0;

        int currentBuffer = 3;
        int bufferCapacity = 5;


        System.out.println("\nTEST INPUT");
        System.out.println("---------------------------------");

        System.out.println(
                "Total Packets       : " + totalPackets
        );

        System.out.println(
                "Transmitted Packets : " + transmittedPackets
        );

        System.out.println(
                "Dropped Packets     : " + droppedPackets
        );

        System.out.println(
                "Packet Size         : " + packetSize + " bytes"
        );

        System.out.println(
                "Time                : " + timeSeconds + " seconds"
        );

        System.out.println(
                "Current Buffer      : " + currentBuffer
        );

        System.out.println(
                "Buffer Capacity     : " + bufferCapacity
        );


        PerformanceAnalyzer.displayPerformance(
                totalPackets,
                transmittedPackets,
                droppedPackets,
                packetSize,
                timeSeconds,
                currentBuffer,
                bufferCapacity
        );


        System.out.println("\n=================================");
        System.out.println(" PERFORMANCE TEST COMPLETE");
        System.out.println("=================================");
    }
}
