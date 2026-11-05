public class PacketGenerator {

    private static int packetCounter = 1;

    public static Packet generatePacket(
            String source,
            String destination,
            String protocol,
            int size) {

        Packet packet = new Packet(
                packetCounter,
                source,
                destination,
                protocol,
                size
        );

        packetCounter++;

        return packet;
    }

    public static int getPacketCountForCongestion(String congestionLevel) {

        if (congestionLevel.equals("LOW")) {
            return 5;
        }
        else if (congestionLevel.equals("MEDIUM")) {
            return 10;
        }
        else {
            return 20;
        }
    }
}
