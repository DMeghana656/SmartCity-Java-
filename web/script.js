async function loadSimulation() {

    try {

        const response = await fetch("/api/simulation");

        const data = await response.json();

        document.getElementById("traffic").innerHTML =
            data.traffic;

        document.getElementById("packets").innerHTML =
            data.packets;

        document.getElementById("buffer").innerHTML =
            data.buffer;

        document.getElementById("leakyBucket").innerHTML =
            data.leakyBucket;

        document.getElementById("performance").innerHTML =
            data.performance;

        document.getElementById("protocol").innerHTML =
            data.protocol;

    }
    catch (error) {

        document.getElementById("traffic").innerHTML =
            "<p>Unable to connect to Java server.</p>";

        console.error(error);
    }
}

loadSimulation();
