public class TcpUdpAnalyzerTest {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" TCP VS UDP ANALYZER TEST");
        System.out.println("=================================");

        TcpUdpAnalyzer.analyzeTCP();

        TcpUdpAnalyzer.analyzeUDP();

        TcpUdpAnalyzer.compareProtocols();

        System.out.println("\n=================================");
        System.out.println(" TCP VS UDP TEST COMPLETE");
        System.out.println("=================================");
    }
}
