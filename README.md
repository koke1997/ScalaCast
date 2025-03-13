# ScalaCast

![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Scala](https://img.shields.io/badge/scala-2.13.12-red.svg)
![Akka](https://img.shields.io/badge/akka-2.7.0-orange.svg)
![Play Framework](https://img.shields.io/badge/play-2.9.0-green.svg)

ScalaCast is a centralized server streaming video server built with Scala, leveraging its strengths in concurrency and distributed programming. The platform provides reliable data transmission, fault tolerance, and various streaming protocols for real-time applications.

## 🚀 Features

- **Centralized Server Architecture**: Efficient client connection handling and communication
- **Reliable Data Transmission**: Built-in handling for packet loss and retransmissions 
- **Fault-Tolerant Design**: Error handling and recovery mechanisms
- **Multiple Streaming Protocols**:
  - HLS (HTTP Live Streaming)
  - DASH (Dynamic Adaptive Streaming over HTTP) 
  - RTMP (Real-Time Messaging Protocol)
  - Adaptive Bitrate Streaming
- **WebRTC Support**: For direct peer-to-peer video connections
- **Peer Discovery**: Built-in mechanism for discovering and communicating with peers
- **Real-time Monitoring**: Track CPU usage, memory usage, and logs for errors
- **SSL Configuration**: Secure your connections with flexible SSL setup
- **WebSockets**: Real-time communication between server and clients
- **Subtitle Support**: Add subtitles to your video streams
- **Microservices Architecture**: Modular design for easier scaling and development
- **Frontend Management Interface**: Simple web interface for system management
- **HTTP/HTTPS Support**: Support for both protocols with automatic HTTPS redirection

## 📋 Requirements

- Java 11+
- Scala 2.13.12
- sbt 1.9.8
- Play Framework 2.9.0
- Akka 2.7.0
- For RTMP streaming: ffmpeg, nginx-rtmp-module

## 🛠️ Installation

### Clone the repository

```bash
git clone https://github.com/koke1997/ScalaCast.git
cd ScalaCast
```

### Install dependencies

**On Linux:**
```bash
./scripts/install_dependencies.sh
```

**On Windows:**
```bash
scripts\install_dependencies.bat
```

### SSL Setup (Optional)

Generate SSL certificates for secure connections:
```bash
./scripts/generate_certificates.sh PEM conf/certs server
```

## 🚀 Running the Application

### Standard Run

```bash
sbt run
```

### Running on Multiple Ports

You can run the application on multiple ports using the provided Python script:

```bash
python scripts/run_local.py --port1 8080 --port2 8081
```

### Deploy to GitHub Pages

The project includes a GitHub Actions workflow for easy deployment to GitHub Pages:

```bash
./scripts/build_static_files.sh
git add docs
git commit -m "Update static files for GitHub Pages"
git push
```

The workflow will automatically deploy to GitHub Pages on push to the main branch.

## 🖥️ API Endpoints

The application exposes various API endpoints for different functionalities:

### Core Services

- **Centralized Server**
  - `GET /api/v1/centralized-server` - Get server status
  - `POST /api/v1/centralized-server` - Start server
  - `DELETE /api/v1/centralized-server` - Stop server

- **Fault Tolerance**
  - `GET /api/v1/fault-tolerance` - Get fault tolerance status
  - `POST /api/v1/fault-tolerance/start` - Start fault tolerance service
  - `POST /api/v1/fault-tolerance/stop` - Stop fault tolerance service

- **Monitoring**
  - `GET /api/v1/monitoring` - Get monitoring status
  - `POST /api/v1/monitoring/cpu` - Monitor CPU
  - `POST /api/v1/monitoring/memory` - Monitor memory
  - `POST /api/v1/monitoring/logs` - Monitor logs
  - `POST /api/v1/monitoring/restart` - Restart service

### Streaming-related Endpoints

- **Video Streaming**
  - `GET /api/v1/video-streaming` - Get video streaming status

- **WebRTC**
  - `GET /webrtc` - WebRTC interface
  - `GET /webrtc/test` - WebRTC test page
  - `GET /simple-cam` - Simple camera test
  - `GET /file-cam` - File camera approach

### Authentication and Management

- **Authentication**
  - `POST /auth/login` - Login
  - `POST /auth/refresh-token` - Refresh token

- **Client Management**
  - `GET /clients` - Get all clients
  - `GET /clients/:clientId` - Get client by ID

- **Configuration**
  - `GET /config/streaming-quality` - Get streaming quality
  - `POST /config/streaming-quality` - Update streaming quality

- **Webhooks**
  - `POST /webhooks` - Create webhook
  - `GET /webhooks/:webhookId` - Get webhook by ID
  - `DELETE /webhooks/:webhookId` - Delete webhook

### Health and Analytics

- **Health Check**
  - `GET /health` - Get health status
  - `GET /metrics` - Get metrics

- **Analytics**
  - `GET /analytics/usage` - Get usage analytics
  - `GET /analytics/performance` - Get performance analytics

## 📡 WebSocket Support

The application supports WebSockets for real-time communication:

```
WebSocket: /ws/video-stream
```

This endpoint allows real-time streaming and communication between clients and the server.

## 🎬 Webcam Testing

The project includes a webcam testing site that allows users to test different streaming options:

1. Open `/webrtc/test` in your browser
2. Test different streaming protocols (Webcam, HLS, DASH, RTMP)
3. Select different camera inputs
4. Add subtitles to your stream

## 🧪 Testing

### Running Unit Tests

```bash
sbt test
```

### Running Integration Tests

```bash
sbt it:test
```

### Test Coverage

```bash
sbt coverage test
```

## 🧩 Architecture

### Controller Layer

The application follows the MVC pattern with separate controllers for each functionality:

- `CentralizedServerController`: Manages the centralized server
- `AuthenticationController`: Handles user authentication
- `WebSocketController`: Manages WebSocket connections
- `VideoStreamingController`: Controls video streaming
- `WebRTCController`: Handles WebRTC connections
- `MonitoringController`: Monitors system resources

### Service Layer

The service layer contains the business logic:

- `CentralizedServerService`: Core server functionality
- `ReliableTransmission`: Ensures reliable data transfer
- `FaultTolerance`: Provides fault tolerance mechanisms
- `VideoStreaming`: Handles video streaming
- `MonitoringService`: Monitors system resources
- `PeerDiscovery`: Discovers and communicates with peers

### Model Layer

The model layer defines data structures:

- `Client`: Represents a client
- `Peer`: Represents a discovered peer
- `Webhook`: Represents a webhook configuration

## 🔧 Configuration

### Configuration Files

- `application.conf`: Main application configuration
- `routes`: Route definitions
- `ssl.conf`: SSL configuration
- `sys.config`: System configuration

### Example Configuration

Here's a sample from `application.conf`:

```conf
# Application settings
app.name = "ScalaCast"
app.version = "1.0.0"
app.description = "A centralized server streaming video server built with Scala."

# SSL Configuration
ssl {
  certificate.path = "/path/to/ssl_certificate.pem"
  certificate.key.path = "/path/to/ssl_certificate_key.pem"
  protocols = ["TLSv1.2", "TLSv1.3"]
  ciphers = ["ECDHE-ECDSA-AES256-GCM-SHA384", "ECDHE-RSA-AES256-GCM-SHA384"]
}

# Video streaming settings
video.streaming {
  adaptive.bitrate.enabled = true
  subtitle.support.enabled = true
}
```

## 📚 Usage Examples

### Start Centralized Server

```scala
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

// Start the centralized server
Await.result(CentralizedServer.startServer(8080), 5.seconds)

// Handle a client request
Await.result(CentralizedServer.handleClientRequest(clientSocket), 5.seconds)

// Stop the centralized server
Await.result(CentralizedServer.stopServer(), 5.seconds)
```

### Configure Video Streaming

```scala
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

// Start adaptive bitrate streaming
Await.result(VideoStreaming.startAdaptiveBitrateStreaming("path/to/video.mp4", "path/to/output"), 5.seconds)

// Start subtitle support
Await.result(VideoStreaming.startSubtitleSupport("path/to/video.mp4", "path/to/subtitles.srt"), 5.seconds)
```

### Set Up SSL

```scala
import scala.util.{Success, Failure}

// Load a key store
val keyStore = SSLConfiguration.loadKeyStore("path/to/keystore.jks", "password", "JKS")

// Create an SSL context
val sslContext = SSLConfiguration.createSSLContext(
  "path/to/keystore.jks", "password", "JKS",
  "path/to/truststore.jks", "password", "JKS"
)

// Apply the SSL context
SSLConfiguration.applySSLContext(sslContext)
```

### Monitoring System Resources

```scala
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

// Monitor CPU usage
Await.result(MonitoringService.monitorCPU(), 5.seconds)

// Monitor memory usage
Await.result(MonitoringService.monitorMemory(), 5.seconds)

// Monitor logs for errors
Await.result(MonitoringService.monitorLogs(), 5.seconds)
```

## 🤝 Contributing

Contributions are welcome! Here's how you can contribute:

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/amazing-feature`
3. Commit your changes: `git commit -m 'Add some amazing feature'`
4. Push to the branch: `git push origin feature/amazing-feature`
5. Open a Pull Request

Please ensure your code passes all tests and follows the project's coding style.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## ❓ Troubleshooting

### Common Issues

**Dependencies not installed correctly**
- Solution: Run the install_dependencies script with administrator privileges

**WebSocket connection issues**
- Solution: Check if port 8080 is open and not blocked by firewall

**SSL certificate problems**
- Solution: Generate new certificates using the provided script

**Camera access denied**
- Solution: Ensure browser permissions for camera access are enabled

## 📧 Contact

If you have any questions or suggestions, please open an issue or contact the repository owner.

---

Made with ❤️ using Scala
