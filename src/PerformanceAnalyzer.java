public class PerformanceAnalyzer {

    // Calculate packet drop rate
    public static double calculateDropRate(
            int droppedPackets,
            int totalPackets) {

        if (totalPackets == 0) {
            return 0.0;
        }

        return ((double) droppedPackets / totalPackets) * 100;
    }


    // Calculate throughput
    // Throughput = transmitted data / time
    public static double calculateThroughput(
            int transmittedPackets,
            int packetSize,
            double timeSeconds) {

        if (timeSeconds == 0) {
            return 0.0;
        }

        double totalDataBits =
                (double) transmittedPackets
                * packetSize
                * 8;

        return totalDataBits / timeSeconds;
    }


    // Calculate data rate
    // Data Rate = total generated data / time
    public static double calculateDataRate(
            int totalPackets,
            int packetSize,
            double timeSeconds) {

        if (timeSeconds == 0) {
            return 0.0;
        }

        double totalDataBits =
                (double) totalPackets
                * packetSize
                * 8;

        return totalDataBits / timeSeconds;
    }


    // Calculate buffer utilization
    public static double calculateBufferUtilization(
            int currentPackets,
            int bufferCapacity) {

        if (bufferCapacity == 0) {
            return 0.0;
        }

        return ((double) currentPackets / bufferCapacity) * 100;
    }


    // Display all performance metrics
    public static void displayPerformance(
            int totalPackets,
            int transmittedPackets,
            int droppedPackets,
            int packetSize,
            double timeSeconds,
            int currentBuffer,
            int bufferCapacity) {

        double dropRate =
                calculateDropRate(
                        droppedPackets,
                        totalPackets
                );

        double throughput =
                calculateThroughput(
                        transmittedPackets,
                        packetSize,
                        timeSeconds
                );

        double dataRate =
                calculateDataRate(
                        totalPackets,
                        packetSize,
                        timeSeconds
                );

        double bufferUtilization =
                calculateBufferUtilization(
                        currentBuffer,
                        bufferCapacity
                );


        System.out.println("\nNETWORK PERFORMANCE METRICS");
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

        System.out.printf(
                "Packet Drop Rate    : %.2f%%%n",
                dropRate
        );

        System.out.printf(
                "Throughput          : %.2f bits/sec%n",
                throughput
        );

        System.out.printf(
                "Data Rate           : %.2f bits/sec%n",
                dataRate
        );

        System.out.printf(
                "Buffer Utilization  : %.2f%%%n",
                bufferUtilization
        );
    }
}
