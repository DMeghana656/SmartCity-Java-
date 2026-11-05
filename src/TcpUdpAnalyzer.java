public class TcpUdpAnalyzer {

    // Display TCP performance
    public static void analyzeTCP() {

        System.out.println("\nTCP PERFORMANCE");
        System.out.println("---------------------------------");

        System.out.println("Protocol            : TCP");
        System.out.println("Connection Type     : Connection-oriented");
        System.out.println("Reliability         : High");
        System.out.println("Packet Loss         : Low");
        System.out.println("Data Delivery       : Reliable");
        System.out.println("Typical Usage       : Web, File Transfer");
    }


    // Display UDP performance
    public static void analyzeUDP() {

        System.out.println("\nUDP PERFORMANCE");
        System.out.println("---------------------------------");

        System.out.println("Protocol            : UDP");
        System.out.println("Connection Type     : Connectionless");
        System.out.println("Reliability         : Lower");
        System.out.println("Packet Loss         : Possible");
        System.out.println("Data Delivery       : Faster");
        System.out.println("Typical Usage       : CCTV, Streaming");
    }


    // Display comparison
    public static void compareProtocols() {

        System.out.println("\nTCP VS UDP COMPARISON");
        System.out.println("---------------------------------");

        System.out.printf(
                "%-20s %-20s %-20s%n",
                "Parameter",
                "TCP",
                "UDP"
        );

        System.out.printf(
                "%-20s %-20s %-20s%n",
                "Connection",
                "Connection-oriented",
                "Connectionless"
        );

        System.out.printf(
                "%-20s %-20s %-20s%n",
                "Reliability",
                "High",
                "Lower"
        );

        System.out.printf(
                "%-20s %-20s %-20s%n",
                "Speed",
                "Lower",
                "Higher"
        );

        System.out.printf(
                "%-20s %-20s %-20s%n",
                "Packet Loss",
                "Low",
                "Possible"
        );

        System.out.printf(
                "%-20s %-20s %-20s%n",
                "Smart City Use",
                "Traffic Control",
                "CCTV / Sensors"
        );
    }
}
