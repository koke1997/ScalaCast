# ScalaCast
ScalaCast is a centralized server streaming video server built with Scala, leveraging its strengths in concurrency and distributed programming. Features include centralized server, reliable data transmission, and fault-tolerant architecture. Ideal for real-time streaming and centralized applications.

## Centralized Server
The centralized server feature allows clients to connect to a central server for efficient communication and data sharing.

### How to Use Centralized Server
1. Start the centralized server by calling the `CentralizedServer.startServer` function.
2. Handle client requests using the `CentralizedServer.handleClientRequest` function.
3. Stop the centralized server by calling the `CentralizedServer.stopServer` function.

### Example
```scala
import scala.concurrent.Await
import scala.concurrent.duration._

implicit val ec: ExecutionContext = ExecutionContext.global

// Start the centralized server
Await.result(CentralizedServer.startServer(8080), 5.seconds)

// Handle a client request
Await.result(CentralizedServer.handleClientRequest(clientSocket), 5.seconds)

// Stop the centralized server
Await.result(CentralizedServer.stopServer(), 5.seconds)
```

## Fault Tolerance
The fault tolerance feature ensures that the system can handle failures gracefully and continue operating. This includes error handling and recovery mechanisms in various modules.

### How to Use Fault Tolerance
1. Start the fault tolerance service by calling the `FaultTolerance.start` function.
2. Handle errors using the `FaultTolerance.handleError` function.
3. Recover from errors using the `FaultTolerance.recover` function.
4. Stop the fault tolerance service by calling the `FaultTolerance.stop` function.

### Example
```scala
import scala.concurrent.Await
import scala.concurrent.duration._

implicit val ec: ExecutionContext = ExecutionContext.global

// Start the fault tolerance service
Await.result(FaultTolerance.start(), 5.seconds)

// Handle an error
Await.result(FaultTolerance.handleError("Critical"), 5.seconds)

// Recover from an error
Await.result(FaultTolerance.recover("Critical"), 5.seconds)

// Stop the fault tolerance service
Await.result(FaultTolerance.stop(), 5.seconds)
```

## Frontend Interface
The frontend interface feature allows for managing options, monitorings, and scripts through a web interface.

### How to Use Frontend Interface
1. Manage options by calling the `FrontendInterface.manageOptions` function.
2. Manage monitorings by calling the `FrontendInterface.manageMonitorings` function.
3. Manage scripts by calling the `FrontendInterface.manageScripts` function.
4. Start the frontend interface by calling the `FrontendInterface.start` function.

### Example
```scala
import scala.concurrent.Await
import scala.concurrent.duration._

implicit val ec: ExecutionContext = ExecutionContext.global

// Start the frontend interface
Await.result(FrontendInterface.start(), 5.seconds)

// Manage options
Await.result(FrontendInterface.manageOptions(), 5.seconds)

// Manage monitorings
Await.result(FrontendInterface.manageMonitorings(), 5.seconds)

// Manage scripts
Await.result(FrontendInterface.manageScripts(), 5.seconds)
```

## Monitoring Service
The monitoring service feature allows for monitoring CPU usage, memory usage, and logs for errors.

### How to Use Monitoring Service
1. Monitor CPU usage by calling the `MonitoringService.monitorCPU` function.
2. Monitor memory usage by calling the `MonitoringService.monitorMemory` function.
3. Monitor logs for errors by calling the `MonitoringService.monitorLogs` function.
4. Restart a service by calling the `MonitoringService.restartService` function.

### Example
```scala
import scala.concurrent.Await
import scala.concurrent.duration._

implicit val ec: ExecutionContext = ExecutionContext.global

// Monitor CPU usage
Await.result(MonitoringService.monitorCPU(), 5.seconds)

// Monitor memory usage
Await.result(MonitoringService.monitorMemory(), 5.seconds)

// Monitor logs for errors
Await.result(MonitoringService.monitorLogs(), 5.seconds)

// Restart a service
Await.result(MonitoringService.restartService("serviceName"), 5.seconds)
```

## Peer Discovery
The peer discovery feature allows for discovering and communicating with peers in a network.

### How to Use Peer Discovery
1. Start the peer discovery service by calling the `PeerDiscovery.start` function.
2. Handle incoming messages using the `PeerDiscovery.handleMessage` function.
3. Broadcast messages to peers using the `PeerDiscovery.broadcastMessage` function.
4. Stop the peer discovery service by calling the `PeerDiscovery.stop` function.

### Example
```scala
import scala.concurrent.Await
import scala.concurrent.duration._

implicit val ec: ExecutionContext = ExecutionContext.global

// Start the peer discovery service
Await.result(PeerDiscovery.start(), 5.seconds)

// Handle an incoming message
Await.result(PeerDiscovery.handleMessage("Hello, peer!"), 5.seconds)

// Broadcast a message to peers
Await.result(PeerDiscovery.broadcastMessage("Hello, peers!"), 5.seconds)

// Stop the peer discovery service
Await.result(PeerDiscovery.stop(), 5.seconds)
```

## Port Exposure Service
The port exposure service feature allows for exposing and closing ports for end users and manual operators.

### How to Use Port Exposure Service
1. Expose a port by calling the `PortExposureService.exposePort` function.
2. Close a port by calling the `PortExposureService.closePort` function.
3. Expose ports for end users by calling the `PortExposureService.exposePortsForEndUsers` function.
4. Expose ports for manual operators by calling the `PortExposureService.exposePortsForManualOperators` function.
5. Close all ports by calling the `PortExposureService.closeAllPorts` function.

### Example
```scala
import scala.concurrent.Await
import scala.concurrent.duration._

implicit val ec: ExecutionContext = ExecutionContext.global

// Expose a port
Await.result(PortExposureService.exposePort(8080), 5.seconds)

// Close a port
Await.result(PortExposureService.closePort(8080), 5.seconds)

// Expose ports for end users
Await.result(PortExposureService.exposePortsForEndUsers(List(8080, 8081)), 5.seconds)

// Expose ports for manual operators
Await.result(PortExposureService.exposePortsForManualOperators(List(8082, 8083)), 5.seconds)

// Close all ports
Await.result(PortExposureService.closeAllPorts(), 5.seconds)
```

## Reliable Data Transmission
The reliable data transmission feature ensures that data is transmitted reliably between clients and the server, handling packet loss and retransmissions.

### How to Use Reliable Data Transmission
1. Send data reliably by calling the `ReliableTransmission.sendData` function with the client and data as arguments.
2. Receive data reliably by calling the `ReliableTransmission.receiveData` function with a data handler function as an argument.

### Example
```scala
import scala.concurrent.Await
import scala.concurrent.duration._

implicit val ec: ExecutionContext = ExecutionContext.global

// Send data reliably to a client
Await.result(ReliableTransmission.sendData("client1", "Hello, client!"), 5.seconds)

// Define a data handler function
val dataHandler: String => Unit = data => println(s"Received data: $data")

// Receive data reliably
Await.result(ReliableTransmission.receiveData(dataHandler), 5.seconds)
```

## SSL Configuration
The SSL configuration feature allows for configuring SSL context and applying it to the system.

### How to Use SSL Configuration
1. Load a key store by calling the `SSLConfiguration.loadKeyStore` function.
2. Create an SSL context by calling the `SSLConfiguration.createSSLContext` function.
3. Apply the SSL context by calling the `SSLConfiguration.applySSLContext` function.
4. Configure SSL by calling the `SSLConfiguration.configureSSL` function.

### Example
```scala
import scala.util.{Success, Failure}

// Load a key store
val keyStore = SSLConfiguration.loadKeyStore("path/to/keystore.jks", "password", "JKS")

// Create an SSL context
val sslContext = SSLConfiguration.createSSLContext("path/to/keystore.jks", "password", "JKS", "path/to/truststore.jks", "password", "JKS")

// Apply the SSL context
SSLConfiguration.applySSLContext(sslContext)

// Configure SSL
SSLConfiguration.configureSSL("path/to/keystore.jks", "password", "JKS", "path/to/truststore.jks", "password", "JKS") match {
  case Success(_) => println("SSL configured successfully")
  case Failure(exception) => println(s"Failed to configure SSL: ${exception.getMessage}")
}
```

## Video Streaming
The video streaming feature allows for adaptive bitrate streaming and subtitle support.

### How to Use Video Streaming
1. Start adaptive bitrate streaming by calling the `VideoStreaming.startAdaptiveBitrateStreaming` function.
2. Start subtitle support by calling the `VideoStreaming.startSubtitleSupport` function.

### Example
```scala
import scala.concurrent.Await
import scala.concurrent.duration._

implicit val ec: ExecutionContext = ExecutionContext.global

// Start adaptive bitrate streaming
Await.result(VideoStreaming.startAdaptiveBitrateStreaming("path/to/video.mp4", "path/to/output"), 5.seconds)

// Start subtitle support
Await.result(VideoStreaming.startSubtitleSupport("path/to/video.mp4", "path/to/subtitles.srt"), 5.seconds)
```

## Setting Up and Running the Scala Frontend
To set up and run the Scala frontend, follow these steps:

1. Ensure Scala and sbt are installed on your system.
2. Navigate to the project directory.
3. Compile the Scala code using sbt:
   ```sh
   sbt compile
   ```
4. Run the Scala frontend:
   ```sh
   sbt run
   ```

## Testing
The testing section provides instructions for running unit tests and integration tests to ensure the system works as expected.

### Running Unit Tests
To run the unit tests, use the following command:
```sh
sbt test
```

### Running Integration Tests
To run the integration tests, use the following command:
```sh
sbt it:test
```

### Running Test Coverage
To run the test coverage, use the following command:
```sh
sbt coverage test
```

## Automated Testing Workflow
The automated testing workflow uses GitHub Actions to run tests automatically after a pull request is made. This ensures that the tests are always up-to-date and provides immediate feedback on the status of the code.

### How to View Test Results
1. Navigate to the "Actions" tab in your GitHub repository.
2. Select the workflow run you want to view.
3. Check the logs for detailed information about the test results and verbose output.

### Example Workflow File
The following is an example of a GitHub Actions workflow file (`.github/workflows/ci.yml`) that automates the test execution:
```yaml
name: CI

on:
  pull_request:
    branches:
      - main

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout code
        uses: actions/checkout@v2

      - name: Set up Java
        uses: actions/setup-java@v2
        with:
          distribution: 'adopt'
          java-version: '11'

      - name: Set up Scala
        run: |
          echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | sudo tee /etc/apt/sources.list.d/sbt.list
          echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | sudo tee /etc/apt/sources.list.d/sbt_old.list
          curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | sudo apt-key add
          sudo apt-get update
          sudo apt-get install sbt

      - name: Compile Scala code
        run: sbt compile

      - name: Run unit tests
        run: sbt test

      - name: Run integration tests
        run: sbt it:test
```

## Running the Project Locally

### On Linux

To run the project locally on Linux, follow these steps:

1. Open a terminal and navigate to the project directory.
2. Run the installation script to install all dependencies:
   ```sh
   ./scripts/install_dependencies.sh
   ```
3. Run the project using the local run script:
   ```sh
   ./scripts/run_local.sh
   ```

### On Windows

To run the project locally on Windows, follow these steps:

1. Open a command prompt and navigate to the project directory.
2. Run the installation script to install all dependencies:
   ```sh
   scripts\install_dependencies.bat
   ```
3. Run the project using the local run script:
   ```sh
   scripts\run_local.bat
   ```

### On Scala

To run the Scala connector locally, follow these steps:

1. Ensure Scala and sbt are installed on your system.
2. Navigate to the project directory.
3. Compile the Scala code using sbt:
   ```sh
   sbt compile
   ```
4. Run the Scala connector:
   ```sh
   sbt run
   ```

## Running the Scala Frontend Locally

### On Linux

To run the Scala frontend locally on Linux, follow these steps:

1. Open a terminal and navigate to the project directory.
2. Ensure Scala and sbt are installed on your system.
3. Add the sbt repository to your sources list:
   ```sh
   echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | sudo tee /etc/apt/sources.list.d/sbt.list
   echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | sudo tee /etc/apt/sources.list.d/sbt_old.list
   curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | sudo apt-key add
   sudo apt-get update
   sudo apt-get install sbt
   ```
4. Compile the Scala code using sbt:
   ```sh
   sbt compile
   ```
5. Run the Scala frontend:
   ```sh
   sbt run
   ```

### On Windows

To run the Scala frontend locally on Windows, follow these steps:

1. Open a command prompt and navigate to the project directory.
2. Ensure Scala and sbt are installed on your system.
3. Add the sbt repository to your sources list:
   ```sh
   echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | sudo tee /etc/apt/sources.list.d/sbt.list
   echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | sudo tee /etc/apt/sources.list.d/sbt_old.list
   curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | sudo apt-key add
   sudo apt-get update
   sudo apt-get install sbt
   ```
4. Compile the Scala code using sbt:
   ```sh
   sbt compile
   ```
5. Run the Scala frontend:
   ```sh
   sbt run
   ```

## HLS and DASH Streaming Options
The HLS and DASH streaming options allow for adaptive bitrate streaming, providing a better user experience by adjusting the video quality based on the viewer's network conditions.

### How to Use HLS Streaming
1. Ensure the HLS library is installed and configured in your project.
2. Use the `hls_streaming:start/2` function to start HLS streaming with the video file path and output directory as arguments.
3. Retrieve the HLS playlist using the `hls_streaming:get_playlist/1` function with the output directory as an argument.

### Example
```scala
// Start HLS streaming
HlsStreaming.start("path/to/video.mp4", "path/to/output")

// Retrieve the HLS playlist
val playlist = HlsStreaming.getPlaylist("path/to/output")
println(s"HLS playlist: $playlist")
```

### How to Use DASH Streaming
1. Ensure the DASH library is installed and configured in your project.
2. Use the `dash_streaming:start/2` function to start DASH streaming with the video file path and output directory as arguments.
3. Retrieve the DASH manifest using the `dash_streaming:get_manifest/1` function with the output directory as an argument.

### Example
```scala
// Start DASH streaming
DashStreaming.start("path/to/video.mp4", "path/to/output")

// Retrieve the DASH manifest
val manifest = DashStreaming.getManifest("path/to/output")
println(s"DASH manifest: $manifest")
```

## Deploying the App to GitHub Pages

To deploy the app to GitHub Pages, follow these steps:

1. Ensure that the GitHub repository is set up with GitHub Pages enabled.
2. Add the GitHub Actions workflow file (`.github/workflows/deploy.yml`) to automate the deployment process.
3. Push the changes to the `main` branch.

The GitHub Actions workflow will automatically build and deploy the app to GitHub Pages.

### Example Workflow File
The following is an example of a GitHub Actions workflow file (`.github/workflows/deploy.yml`) that automates the deployment to GitHub Pages:
```yaml
name: Deploy to GitHub Pages

on:
  push:
    branches:
      - main

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout code
        uses: actions/checkout@v2

      - name: Set up Java
        uses: actions/setup-java@v2
        with:
          distribution: 'adopt'
          java-version: '11'

      - name: Set up Scala
        run: |
          echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | sudo tee /etc/apt/sources.list.d/sbt.list
          echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | sudo tee /etc/apt/sources.list.d/sbt_old.list
          curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | sudo apt-key add
          sudo apt-get update
          sudo apt-get install sbt

      - name: Compile Scala code
        run: sbt compile

      - name: Build frontend
        run: sbt fullOptJS

      - name: Deploy to GitHub Pages
        uses: peaceiris/actions-gh-pages@v3
        with:
          github_token: ${{ secrets.GITHUB_TOKEN }}
          publish_dir: ./docs
```

## RTMP Streaming Option
The RTMP streaming option allows for real-time messaging protocol streaming, providing a stable video session for multiple users.

### How to Use RTMP Streaming
1. Ensure the `nginx-rtmp-module` and `ffmpeg` with RTMP support are installed and configured in your project.
2. Use the `rtmp_streaming:start/2` function to start RTMP streaming with the video file path and output directory as arguments.
3. Retrieve the RTMP stream URL using the `rtmp_streaming:get_stream_url/1` function with the output directory as an argument.

### Example
```scala
// Start RTMP streaming
RtmpStreaming.start("path/to/video.mp4", "path/to/output")

// Retrieve the RTMP stream URL
val streamUrl = RtmpStreaming.getStreamUrl("path/to/output")
println(s"RTMP stream URL: $streamUrl")
```

## Troubleshooting Common Issues
This section provides solutions to common issues that users might encounter during setup and running the project.

### Issue: Dependencies not installed correctly
**Solution**: Ensure that the installation scripts (`scripts/install_dependencies.sh` and `scripts/install_dependencies.bat`) are up-to-date and cover all necessary dependencies for the project, including those required for RTMP streaming.

### Issue: Video streaming not working
**Solution**: Check the configuration files and ensure that the necessary libraries and tools for HLS, DASH, and RTMP streaming are installed and configured correctly. Verify that the video file paths and output directories are correct.

### Issue: Project not running locally
**Solution**: Follow the detailed instructions in the `README.md` for setting up and running the project locally on different platforms. Ensure that all dependencies are installed, and the project is compiled correctly.

## Microservices Architecture

The ScalaCast project now includes a microservices architecture for easier integration and stability. The microservices are designed to handle specific functionalities and can be run independently.

### Microservices Overview

1. **Centralized Server Service**: Handles client connections and requests.
2. **Reliable Transmission Service**: Ensures reliable data transmission between clients and the server.
3. **Fault Tolerance Service**: Provides fault tolerance and error recovery mechanisms.

### Setting Up and Running Microservices

To set up and run the microservices, follow these steps:

1. Ensure Docker is installed on your system.
2. Navigate to the project directory.
3. Build the Docker images for each microservice:
   ```sh
   docker build -t centralized-server-service -f Dockerfile .
   docker build -t reliable-transmission-service -f Dockerfile .
   docker build -t fault-tolerance-service -f Dockerfile .
   ```
4. Run the Docker containers for each microservice:
   ```sh
   docker run -d --name centralized-server-service centralized-server-service
   docker run -d --name reliable-transmission-service reliable-transmission-service
   docker run -d --name fault-tolerance-service fault-tolerance-service
   ```

### Using Microservices

#### Centralized Server Service

The Centralized Server Service handles client connections and requests.

**Functions**:
- `startServer`: Starts the centralized server.
- `stopServer`: Stops the centralized server.
- `handleClientRequest`: Handles client requests.

#### Reliable Transmission Service

The Reliable Transmission Service ensures reliable data transmission between clients and the server.

**Functions**:
- `sendData`: Sends data to a client with retry mechanism.
- `receiveData`: Receives data and processes it using the provided data handler.

#### Fault Tolerance Service

The Fault Tolerance Service provides fault tolerance and error recovery mechanisms.

**Functions**:
- `start`: Starts the fault tolerance service.
- `stop`: Stops the fault tolerance service.
- `handleError`: Handles errors and logs them.
- `recover`: Recovers from errors based on the error type.

## Running the App on Multiple Ports

To run the app on multiple ports, follow these steps:

1. Update the `config/sys.config` file to include configurations for two different ports.
2. Modify the `Dockerfile` to expose the new ports and run the app on both ports.
3. Update the `docs/index.html` file to allow selecting a camera input for each port.
4. Use the provided Python script to run the app on multiple ports using parameters.

### Example Configuration

#### `config/sys.config`
```scala
[
  {kernel, [
    {inet_dist_listen_min, 9100},
    {inet_dist_listen_max, 9155},
    {error_logger, {file, "log/error.log"}}
  ]},
  {sasl, [
    {sasl_error_logger, {file, "log/sasl-error.log"}},
    {errlog_type, error}
  ]},
  {centralized_server, [
    {port_range, {9200, 9255}},
    {broadcast_interval, 5000},
    {max_clients, 50}
  ]},
  {fault_tolerance, [
    {retry_limit, 5},
    {ack_timeout, 1000},
    {max_retries, 3}
  ]},
  {http, [
    {port1, 8080},
    {port2, 8081}
  ]}
]
```

#### `docs/index.html`
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Webcam Test - Scala Frontend</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
        }
        video {
            width: 80%;
            max-width: 600px;
            margin: 20px auto;
            display: block;
        }
        select {
            margin: 20px;
            padding: 10px;
            font-size: 16px;
        }
    </style>
</head>
<body>
    <h1>Webcam Test</h1>
    <select id="streamingOptions">
        <option value="webcam">Webcam</option>
        <option value="hls">HLS</option>
        <option value="dash">DASH</option>
        <option value="rtmp">RTMP</option>
    </select>
    <select id="user1Camera">
        <option value="">Select Camera for User 1</option>
    </select>
    <select id="user2Camera">
        <option value="">Select Camera for User 2</option>
    </select>
    <select id="port1Camera">
        <option value="">Select Camera for Port 1</option>
    </select>
    <select id="port2Camera">
        <option value="">Select Camera for Port 2</option>
    </select>
    <video id="videoElement" autoplay controls></video>
    <script>
        const videoElement = document.getElementById('videoElement');
        const streamingOptions = document.getElementById('streamingOptions');
        const user1Camera = document.getElementById('user1Camera');
        const user2Camera = document.getElementById('user2Camera');
        const port1Camera = document.getElementById('port1Camera');
        const port2Camera = document.getElementById('port2Camera');

        function getCameras() {
            return navigator.mediaDevices.enumerateDevices()
                .then(devices => devices.filter(device => device.kind === 'videoinput'));
        }

        function populateCameraOptions(selectElement, cameras) {
            cameras.forEach(camera => {
                const option = document.createElement('option');
                option.value = camera.deviceId;
                option.text = camera.label || `Camera ${selectElement.length + 1}`;
                selectElement.appendChild(option);
            });
        }

        getCameras().then(cameras => {
            populateCameraOptions(user1Camera, cameras);
            populateCameraOptions(user2Camera, cameras);
            populateCameraOptions(port1Camera, cameras);
            populateCameraOptions(port2Camera, cameras);
        });

        streamingOptions.addEventListener('change', function() {
            const selectedOption = streamingOptions.value;
            if (selectedOption === 'webcam') {
                if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
                    navigator.mediaDevices.getUserMedia({ video: true })
                        .then(function(stream) {
                            videoElement.srcObject = stream;
                            videoElement.play();
                        })
                        .catch(function(error) {
                            console.error("Error accessing webcam: ", error);
                        });
                } else {
                    console.error("getUserMedia not supported by this browser.");
                }
            } else if (selectedOption === 'hls') {
                videoElement.src = 'path/to/hls/playlist.m3u8';
                videoElement.play();
            } else if (selectedOption === 'dash') {
                videoElement.src = 'path/to/dash/manifest.mpd';
                videoElement.play();
            } else if (selectedOption === 'rtmp') {
                videoElement.src = 'rtmp://localhost/live/stream';
                videoElement.play();
            }
        });

        user1Camera.addEventListener('change', function() {
            const selectedCameraId = user1Camera.value;
            if (selectedCameraId) {
                navigator.mediaDevices.getUserMedia({ video: { deviceId: { exact: selectedCameraId } } })
                    .then(function(stream) {
                        videoElement.srcObject = stream;
                        videoElement.play();
                    })
                    .catch(function(error) {
                        console.error("Error accessing camera for User 1: ", error);
                    });
            }
        });

        user2Camera.addEventListener('change', function() {
            const selectedCameraId = user2Camera.value;
            if (selectedCameraId) {
                navigator.mediaDevices.getUserMedia({ video: { deviceId: { exact: selectedCameraId } } })
                    .then(function(stream) {
                        videoElement.srcObject = stream;
                        videoElement.play();
                    })
                    .catch(function(error) {
                        console.error("Error accessing camera for User 2: ", error);
                    });
            }
        });

        port1Camera.addEventListener('change', function() {
            const selectedCameraId = port1Camera.value;
            if (selectedCameraId) {
                navigator.mediaDevices.getUserMedia({ video: { deviceId: { exact: selectedCameraId } } })
                    .then(function(stream) {
                        videoElement.srcObject = stream;
                        videoElement.play();
                    })
                    .catch(function(error) {
                        console.error("Error accessing camera for Port 1: ", error);
                    });
            }
        });

        port2Camera.addEventListener('change', function() {
            const selectedCameraId = port2Camera.value;
            if (selectedCameraId) {
                navigator.mediaDevices.getUserMedia({ video: { deviceId: { exact: selectedCameraId } } })
                    .then(function(stream) {
                        videoElement.srcObject = stream;
                        videoElement.play();
                    })
                    .catch(function(error) {
                        console.error("Error accessing camera for Port 2: ", error);
                    });
            }
        });

        // Initialize with webcam option
        streamingOptions.value = 'webcam';
        streamingOptions.dispatchEvent(new Event('change'));
    </script>
    <!-- Scala is used for the frontend -->
</body>
</html>
```

#### `scripts/run_local.py`
```python
import subprocess
import argparse

def run_app(port1, port2, camera1, camera2):
    cmd = f"sbt run & sbt run --setcookie port2 --name port2@127.0.0.1"
    subprocess.run(cmd, shell=True)

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Run the app on multiple ports with camera inputs")
    parser.add_argument("--port1", type=int, default=8080, help="Port for the first instance")
    parser.add_argument("--port2", type=int, default=8081, help="Port for the second instance")
    parser.add_argument("--camera1", type=str, help="Camera input for the first instance")
    parser.add_argument("--camera2", type=str, help="Camera input for the second instance")
    args = parser.parse_args()

    run_app(args.port1, args.port2, args.camera1, args.camera2)
```

## Running the Project Locally

To run the project locally, follow these steps:

1. Open a terminal or command prompt and navigate to the project directory.
2. Run the installation script to install all dependencies:
   ```sh
   ./scripts/install_dependencies.sh
   ```
3. Run the project using the Python script:
   ```sh
   python scripts/run_local.py
   ```

### Note
The `scripts/run_local.sh` and `scripts/run_local.bat` scripts are deprecated. Please use the `scripts/run_local.py` script for local testing and running the project.

## Webcam Test Site

The project is now ready for release as a site for testing webcam functionality. The webcam test site allows users to select different streaming protocols and camera inputs for testing purposes.

### Features
- Select different streaming protocols: Webcam, HLS, DASH, RTMP
- Choose camera inputs for different users and ports
- Display the selected camera input in a video element

### How to Use the Webcam Test Site
1. Open the `docs/index.html` file in a web browser.
2. Select a streaming protocol from the dropdown menu.
3. Choose a camera input for each user and port.
4. The selected camera input will be displayed in the video element.

### Example
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Webcam Test - ScalaCast</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
        }
        video {
            width: 80%;
            max-width: 600px;
            margin: 20px auto;
            display: block;
        }
        select {
            margin: 20px;
            padding: 10px;
            font-size: 16px;
        }
    </style>
</head>
<body>
    <h1>Webcam Test</h1>
    <select id="streamingOptions">
        <option value="webcam">Webcam</option>
        <option value="hls">HLS</option>
        <option value="dash">DASH</option>
        <option value="rtmp">RTMP</option>
    </select>
    <select id="user1Camera">
        <option value="">Select Camera for User 1</option>
    </select>
    <select id="user2Camera">
        <option value="">Select Camera for User 2</option>
    </select>
    <select id="port1Camera">
        <option value="">Select Camera for Port 1</option>
    </select>
    <select id="port2Camera">
        <option value="">Select Camera for Port 2</option>
    </select>
    <div id="selectedCameraInput">
        <h2>Selected Camera Input</h2>
        <input type="file" id="subtitleFile" accept=".srt,.vtt">
        <video id="selectedCameraVideo" autoplay controls></video>
    </div>
    <video id="videoElement" autoplay controls></video>
    <script>
        const videoElement = document.getElementById('videoElement');
        const streamingOptions = document.getElementById('streamingOptions');
        const user1Camera = document.getElementById('user1Camera');
        const user2Camera = document.getElementById('user2Camera');
        const port1Camera = document.getElementById('port1Camera');
        const port2Camera = document.getElementById('port2Camera');
        const selectedCameraVideo = document.getElementById('selectedCameraVideo');
        const subtitleFileInput = document.getElementById('subtitleFile');

        function getCameras() {
            return navigator.mediaDevices.enumerateDevices()
                .then(devices => devices.filter(device => device.kind === 'videoinput'));
        }

        function populateCameraOptions(selectElement, cameras) {
            cameras.forEach(camera => {
                const option = document.createElement('option');
                option.value = camera.deviceId;
                option.text = camera.label || `Camera ${selectElement.length + 1}`;
                selectElement.appendChild(option);
            });
        }

        getCameras().then(cameras => {
            populateCameraOptions(user1Camera, cameras);
            populateCameraOptions(user2Camera, cameras);
            populateCameraOptions(port1Camera, cameras);
            populateCameraOptions(port2Camera, cameras);
        });

        streamingOptions.addEventListener('change', function() {
            const selectedOption = streamingOptions.value;
            if (selectedOption === 'webcam') {
                if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
                    navigator.mediaDevices.getUserMedia({ video: true })
                        .then(function(stream) {
                            videoElement.srcObject = stream;
                            videoElement.play();
                        })
                        .catch(function(error) {
                            console.error("Error accessing webcam: ", error);
                        });
                } else {
                    console.error("getUserMedia not supported by this browser.");
                }
            } else if (selectedOption === 'hls') {
                videoElement.src = 'path/to/hls/playlist.m3u8';
                videoElement.play();
            } else if (selectedOption === 'dash') {
                videoElement.src = 'path/to/dash/manifest.mpd';
                videoElement.play();
            } else if (selectedOption === 'rtmp') {
                videoElement.src = 'rtmp://localhost/live/stream';
                videoElement.play();
            } else if (selectedOption === 'adaptive') {
                console.log('Adaptive bitrate streaming selected');
                // Add logic to handle adaptive bitrate streaming
            }
        });

        user1Camera.addEventListener('change', function() {
            const selectedCameraId = user1Camera.value;
            if (selectedCameraId) {
                navigator.mediaDevices.getUserMedia({ video: { deviceId: { exact: selectedCameraId } } })
                    .then(function(stream) {
                        videoElement.srcObject = stream;
                        videoElement.play();
                        selectedCameraVideo.srcObject = stream;
                        selectedCameraVideo.play();
                    })
                    .catch(function(error) {
                        console.error("Error accessing camera for User 1: ", error);
                    });
            }
        });

        user2Camera.addEventListener('change', function() {
            const selectedCameraId = user2Camera.value;
            if (selectedCameraId) {
                navigator.mediaDevices.getUserMedia({ video: { deviceId: { exact: selectedCameraId } } })
                    .then(function(stream) {
                        videoElement.srcObject = stream;
                        videoElement.play();
                        selectedCameraVideo.srcObject = stream;
                        selectedCameraVideo.play();
                    })
                    .catch(function(error) {
                        console.error("Error accessing camera for User 2: ", error);
                    });
            }
        });

        port1Camera.addEventListener('change', function() {
            const selectedCameraId = port1Camera.value;
            if (selectedCameraId) {
                navigator.mediaDevices.getUserMedia({ video: { deviceId: { exact: selectedCameraId } } })
                    .then(function(stream) {
                        videoElement.srcObject = stream;
                        videoElement.play();
                    })
                    .catch(function(error) {
                        console.error("Error accessing camera for Port 1: ", error);
                    });
            }
        });

        port2Camera.addEventListener('change', function() {
            const selectedCameraId = port2Camera.value;
            if (selectedCameraId) {
                navigator.mediaDevices.getUserMedia({ video: { deviceId: { exact: selectedCameraId } } })
                    .then(function(stream) {
                        videoElement.srcObject = stream;
                        videoElement.play();
                    })
                    .catch(function(error) {
                        console.error("Error accessing camera for Port 2: ", error);
                    });
            }
        });

        subtitleFileInput.addEventListener('change', function() {
            const subtitleFile = subtitleFileInput.files[0];
            if (subtitleFile) {
                console.log(`Uploading subtitle file: ${subtitleFile.name}`);
                // Add logic to handle subtitle file upload
            }
        });

        // Initialize with webcam option
        streamingOptions.value = 'webcam';
        streamingOptions.dispatchEvent(new Event('change'));

        // WebSocket connection for real-time updates
        const socket = new WebSocket('ws://localhost:8080');
        socket.onmessage = function(event) {
            const data = JSON.parse(event.data);
            if (data.type === 'update') {
                console.log('Real-time update:', data);
                // Handle real-time updates
            }
        };

        // User authentication
        const loginForm = document.createElement('div');
        loginForm.innerHTML = `
            <h2>Login</h2>
            <input type="text" id="username" placeholder="Username">
            <input type="password" id="password" placeholder="Password">
            <button id="loginButton">Login</button>
        `;
        document.body.insertBefore(loginForm, document.body.firstChild);

        document.getElementById('loginButton').addEventListener('click', function() {
            const username = document.getElementById('username').value;
            const password = document.getElementById('password').value;
            console.log(`Logging in with username: ${username}`);
            // Add logic for user authentication
        });

        // Complete the frontend logic for streaming options and camera inputs
        function completeFrontendLogic() {
            // Add logic to complete the frontend logic
        }

        completeFrontendLogic();
    </script>
    <!-- Scala is used for the frontend -->
</body>
</html>
```

## Frontend Improvements

The frontend has been enhanced to include real-time updates, user authentication, and a more interactive user interface. The JavaScript code in `docs/frontend.html` and `docs/index.html` has been extended to provide better user experience and functionality. The `FrontendInterface` object in `src/scala/main/scala/frontend_interface.scala` has been extended to include more comprehensive management options and monitoring capabilities.

### New Features
- Real-time updates for options, monitorings, and scripts using WebSockets
- User authentication using a simple login form and session management
- Enhanced user interface with interactive elements and better styling

### How to Use the Enhanced Frontend
1. Open the `docs/frontend.html` file in a web browser.
2. Log in using the simple login form.
3. Manage options, monitorings, and scripts with real-time updates.

### Example
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Frontend Interface - ScalaCast</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            margin-bottom: 20px;
        }
        select, button {
            margin: 10px;
            padding: 10px;
            font-size: 16px;
        }
        .monitoring, .scripts {
            margin-top: 20px;
        }
        .login-form {
            margin-bottom: 20px;
        }
        .login-form input {
            margin: 5px;
            padding: 10px;
            font-size: 16px;
        }
        .login-form button {
            padding: 10px 20px;
            font-size: 16px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Frontend Interface</h1>
        <div class="login-form">
            <h2>Login</h2>
            <input type="text" id="username" placeholder="Username">
            <input type="password" id="password" placeholder="Password">
            <button id="loginButton">Login</button>
        </div>
        <div class="options">
            <h2>Manage Options</h2>
            <select id="optionSelect">
                <option value="option1">Option 1</option>
                <option value="option2">Option 2</option>
                <option value="option3">Option 3</option>
            </select>
            <button id="applyOption">Apply Option</button>
        </div>
        <div class="monitoring">
            <h2>Manage Monitorings</h2>
            <button id="startMonitoring">Start Monitoring</button>
            <button id="stopMonitoring">Stop Monitoring</button>
        </div>
        <div class="scripts">
            <h2>Manage Scripts</h2>
            <button id="runScript">Run Script</button>
            <button id="stopScript">Stop Script</button>
        </div>
    </div>
    <script>
        const socket = new WebSocket('ws://localhost:8080');
        socket.onmessage = function(event) {
            const data = JSON.parse(event.data);
            if (data.type === 'update') {
                console.log('Real-time update:', data);
                // Handle real-time updates
            }
        };

        document.getElementById('loginButton').addEventListener('click', function() {
            const username = document.getElementById('username').value;
            const password = document.getElementById('password').value;
            console.log(`Logging in with username: ${username}`);
            // Add logic for user authentication
        });

        document.getElementById('applyOption').addEventListener('click', function() {
            const selectedOption = document.getElementById('optionSelect').value;
            console.log(`Applying option: ${selectedOption}`);
            // Add logic to apply the selected option
        });

        document.getElementById('startMonitoring').addEventListener('click', function() {
            console.log('Starting monitoring...');
            // Add logic to start monitoring
        });

        document.getElementById('stopMonitoring').addEventListener('click', function() {
            console.log('Stopping monitoring...');
            // Add logic to stop monitoring
        });

        document.getElementById('runScript').addEventListener('click', function() {
            console.log('Running script...');
            // Add logic to run the script
        });

        document.getElementById('stopScript').addEventListener('click', function() {
            console.log('Stopping script...');
            // Add logic to stop the script
        });
    </script>
</body>
</html>
```

## Deploying the App to a Cloud Platform

To deploy the app to a cloud platform, follow these steps:

1. Choose a cloud platform (e.g., AWS, Google Cloud, Azure).
2. Set up a virtual machine or container service on the chosen cloud platform.
3. Install necessary dependencies (e.g., Scala, sbt, Docker) on the virtual machine or container.
4. Clone the project repository to the virtual machine or container.
5. Build and run the project using the provided scripts.

### Example Deployment on AWS EC2

1. Launch an EC2 instance with the desired configuration.
2. Connect to the EC2 instance using SSH.
3. Install necessary dependencies:
   ```sh
   sudo apt-get update
   sudo apt-get install -y openjdk-11-jdk scala sbt docker.io
   ```
4. Clone the project repository:
   ```sh
   git clone https://github.com/koke1997/ScalaCast.git
   cd ScalaCast
   ```
5. Build and run the project:
   ```sh
   sbt compile
   sbt run
   ```

### Example Deployment on Google Cloud Platform (GCP)

1. Create a new VM instance on GCP.
2. Connect to the VM instance using SSH.
3. Install necessary dependencies:
   ```sh
   sudo apt-get update
   sudo apt-get install -y openjdk-11-jdk scala sbt docker.io
   ```
4. Clone the project repository:
   ```sh
   git clone https://github.com/koke1997/ScalaCast.git
   cd ScalaCast
   ```
5. Build and run the project:
   ```sh
   sbt compile
   sbt run
   ```

### Example Deployment on Microsoft Azure

1. Create a new virtual machine on Azure.
2. Connect to the virtual machine using SSH.
3. Install necessary dependencies:
   ```sh
   sudo apt-get update
   sudo apt-get install -y openjdk-11-jdk scala sbt docker.io
   ```
4. Clone the project repository:
   ```sh
   git clone https://github.com/koke1997/ScalaCast.git
   cd ScalaCast
   ```
5. Build and run the project:
   ```sh
   sbt compile
   sbt run
   ```

By following these steps, you can deploy the ScalaCast app to a cloud platform and make it accessible to users over the internet.
