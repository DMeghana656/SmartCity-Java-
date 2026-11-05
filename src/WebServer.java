import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class WebServer {

    public static void main(String[] args) throws Exception {

        HttpServer server =
                HttpServer.create(
                        new InetSocketAddress(8080),
                        0
                );

        server.createContext("/", WebServer::sendHomePage);
        server.createContext("/style.css", WebServer::sendStyle);
        server.createContext("/script.js", WebServer::sendScript);
        server.createContext("/api/simulation", WebServer::sendSimulationData);
server.createContext("/SmartCity.pkt", WebServer::sendPacketTracerFile);
        server.setExecutor(null);
        server.start();

        System.out.println("Smart City Web Server started!");
        System.out.println("Open: http://localhost:8080");
    }


    private static void sendHomePage(
            HttpExchange exchange) throws IOException {

        sendFile(
                exchange,
                Path.of("web", "index.html"),
                "text/html"
        );
    }


    private static void sendStyle(
            HttpExchange exchange) throws IOException {

        sendFile(
                exchange,
                Path.of("web", "style.css"),
                "text/css"
        );
    }


    private static void sendScript(
            HttpExchange exchange) throws IOException {

        sendFile(
                exchange,
                Path.of("web", "script.js"),
                "application/javascript"
        );
    }
private static void sendPacketTracerFile(
        HttpExchange exchange) throws IOException {

    sendFile(
            exchange,
            Path.of("web", "SmartCity.pkt"),
            "application/octet-stream"
    );
}

    private static void sendFile(
            HttpExchange exchange,
            Path file,
            String contentType)
            throws IOException {

        byte[] response =
                Files.readAllBytes(file);

        exchange.getResponseHeaders()
                .set("Content-Type", contentType);

        exchange.sendResponseHeaders(
                200,
                response.length
        );

        OutputStream output =
                exchange.getResponseBody();

        output.write(response);
        output.close();
    }


    private static void sendSimulationData(
            HttpExchange exchange)
            throws IOException {

        TrafficData mainRoad =
                new TrafficData("Main Road", 120);

        TrafficData hospitalRoad =
                new TrafficData("Hospital Road", 35);

        TrafficData schoolRoad =
                new TrafficData("School Road", 90);

        TrafficData marketRoad =
                new TrafficData("Market Road", 150);

        TrafficData busStation =
                new TrafficData("Bus Station", 70);

        TrafficData railwayRoad =
                new TrafficData("Railway Road", 180);


        String mainCongestion =
                CongestionAnalyzer.analyze(
                        mainRoad.getVehicleCount());

        String hospitalCongestion =
                CongestionAnalyzer.analyze(
                        hospitalRoad.getVehicleCount());

        String schoolCongestion =
                CongestionAnalyzer.analyze(
                        schoolRoad.getVehicleCount());

        String marketCongestion =
                CongestionAnalyzer.analyze(
                        marketRoad.getVehicleCount());

        String busCongestion =
                CongestionAnalyzer.analyze(
                        busStation.getVehicleCount());

        String railwayCongestion =
                CongestionAnalyzer.analyze(
                        railwayRoad.getVehicleCount());


        int packetCount =
                PacketGenerator
                        .getPacketCountForCongestion(
                                mainCongestion);


        BufferManager buffer =
                new BufferManager(10);


        for (int i = 0; i < packetCount; i++) {

            Packet packet =
                    PacketGenerator.generatePacket(
                            "Main Road",
                            "Traffic Management Center",
                            "TCP",
                            1024);

            buffer.addPacket(packet);
        }


        int bufferRejected =
                buffer.getTotalRejected();


        LeakyBucket bucket =
                new LeakyBucket(10);


        int packetsInBuffer =
                buffer.getCurrentSize();


        for (int i = 0;
             i < packetsInBuffer;
             i++) {

            Packet packet =
                    buffer.removePacket();

            if (packet != null) {
                bucket.addPacket(packet);
            }
        }


        int packetsToTransmit =
                bucket.getCurrentPackets();


        for (int i = 0;
             i < packetsToTransmit;
             i++) {

            bucket.transmitPacket();
        }


        int transmitted =
                bucket.getTransmittedPackets();

        int bucketDropped =
                bucket.getDroppedPackets();

        int totalDropped =
                bufferRejected + bucketDropped;


        double dropRate =
                PerformanceAnalyzer.calculateDropRate(
                        totalDropped,
                        packetCount);


        double throughput =
                PerformanceAnalyzer.calculateThroughput(
                        transmitted,
                        1024,
                        5.0);


        double dataRate =
                PerformanceAnalyzer.calculateDataRate(
                        packetCount,
                        1024,
                        5.0);


        String traffic =

                "<table>"
                + "<tr>"
                + "<th>Road</th>"
                + "<th>Vehicles</th>"
                + "<th>Congestion</th>"
                + "</tr>"

                + row("Main Road", 120, mainCongestion)
                + row("Hospital Road", 35, hospitalCongestion)
                + row("School Road", 90, schoolCongestion)
                + row("Market Road", 150, marketCongestion)
                + row("Bus Station", 70, busCongestion)
                + row("Railway Road", 180, railwayCongestion)

                + "</table>";


        String packets =

                "<div class='info-grid'>"

                + infoBox(
                    "Main Road Congestion",
                    mainCongestion)

                + infoBox(
                    "Packets Generated",
                    String.valueOf(packetCount))

                + infoBox(
                    "Packet Size",
                    "1024 bytes")

                + "</div>";


        String bufferData =

                "<div class='info-grid'>"

                + infoBox(
                    "Buffer Capacity",
                    buffer.getCapacity() + " packets")

                + infoBox(
                    "Packets Accepted",
                    String.valueOf(
                        buffer.getTotalAdded()))

                + infoBox(
                    "Packets Rejected",
                    String.valueOf(
                        bufferRejected))

                + infoBox(
                    "Current Buffer",
                    String.valueOf(
                        buffer.getCurrentSize()))

                + "</div>";


        String leakyBucket =

                "<div class='info-grid'>"

                + infoBox(
                    "Bucket Capacity",
                    bucket.getCapacity() + " packets")

                + infoBox(
                    "Packets Received",
                    String.valueOf(
                        bucket.getTotalPackets()))

                + infoBox(
                    "Transmitted",
                    String.valueOf(
                        transmitted))

                + infoBox(
                    "Dropped",
                    String.valueOf(
                        bucketDropped))

                + "</div>";


        String performance =

                "<div class='stats'>"

                + statCard(
                    "Total Packets",
                    String.valueOf(packetCount))

                + statCard(
                    "Transmitted",
                    String.valueOf(transmitted))

                + statCard(
                    "Dropped",
                    String.valueOf(totalDropped))

                + statCard(
                    "Drop Rate",
                    String.format(
                        "%.2f%%",
                        dropRate))

                + statCard(
                    "Throughput",
                    String.format(
                        "%.2f bits/sec",
                        throughput))

                + statCard(
                    "Data Rate",
                    String.format(
                        "%.2f bits/sec",
                        dataRate))

                + "</div>"

                + "<br>"

                + "<div class='info-box'>"
                + "<strong>Simulation Time</strong>"
                + "5 seconds"
                + "</div>";


        String protocol =

                "<table>"

                + "<tr>"
                + "<th>Parameter</th>"
                + "<th>TCP</th>"
                + "<th>UDP</th>"
                + "</tr>"

                + "<tr>"
                + "<td>Connection</td>"
                + "<td>Connection-oriented</td>"
                + "<td>Connectionless</td>"
                + "</tr>"

                + "<tr>"
                + "<td>Reliability</td>"
                + "<td>High</td>"
                + "<td>Lower</td>"
                + "</tr>"

                + "<tr>"
                + "<td>Speed</td>"
                + "<td>Lower</td>"
                + "<td>Higher</td>"
                + "</tr>"

                + "<tr>"
                + "<td>Packet Loss</td>"
                + "<td>Low</td>"
                + "<td>Possible</td>"
                + "</tr>"

                + "<tr>"
                + "<td>Smart City Use</td>"
                + "<td>Traffic Control</td>"
                + "<td>CCTV / Sensors</td>"
                + "</tr>"

                + "</table>";


        String json =j

                "{"

                + "\"traffic\":\""
                + escapeJson(traffic)
                + "\","

                + "\"packets\":\""
                + escapeJson(packets)
                + "\","

                + "\"buffer\":\""
                + escapeJson(bufferData)
                + "\","

                + "\"leakyBucket\":\""
                + escapeJson(leakyBucket)
                + "\","

                + "\"performance\":\""
                + escapeJson(performance)
                + "\","

                + "\"protocol\":\""
                + escapeJson(protocol)
                + "\""

                + "}";


        byte[] response =
                json.getBytes(
                        StandardCharsets.UTF_8);


        exchange.getResponseHeaders()
                .set(
                    "Content-Type",
                    "application/json; charset=UTF-8");


        exchange.sendResponseHeaders(
                200,
                response.length);


        OutputStream output =
                exchange.getResponseBody();

        output.write(response);
        output.close();
    }


    private static String infoBox(
            String title,
            String value) {

        return "<div class='info-box'>"
                + "<strong>"
                + title
                + "</strong>"
                + value
                + "</div>";
    }


    private static String statCard(
            String title,
            String value) {

        return "<div class='card'>"
                + "<h3>"
                + title
                + "</h3>"
                + "<p>"
                + value
                + "</p>"
                + "</div>";
    }


    private static String row(
            String road,
            int vehicles,
            String congestion) {

        String cssClass =
                congestion.toLowerCase();

        return "<tr>"
                + "<td>" + road + "</td>"
                + "<td>" + vehicles + "</td>"
                + "<td><span class='badge "
                + cssClass
                + "'>"
                + congestion
                + "</span></td>"
                + "</tr>";
    }


    private static String escapeJson(
            String text) {

        return text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "")
                .replace("\r", "");
    }
}
