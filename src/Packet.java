public class Packet {

    private int packetId;
    private String source;
    private String destination;
    private String protocol;
    private int size;

    public Packet(
            int packetId,
            String source,
            String destination,
            String protocol,
            int size) {

        this.packetId = packetId;
        this.source = source;
        this.destination = destination;
        this.protocol = protocol;
        this.size = size;
    }

    public int getPacketId() {
        return packetId;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getProtocol() {
        return protocol;
    }

    public int getSize() {
        return size;
    }

    public void displayPacket() {

        System.out.println(
                "Packet " + packetId
                + " | Source: " + source
                + " | Destination: " + destination
                + " | Protocol: " + protocol
                + " | Size: " + size + " bytes"
        );
    }
}
