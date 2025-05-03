# BTLeTool

<!-- PROJECT SHIELDS -->
[![MIT License][license-shield]][license-url]

<!-- PROJECT LOGO -->
<div align="center">
  <h3 align="center">BTLeTool</h3>

  <p align="center">
    An Android app for analyzing Bluetooth Low Energy packet captures from BTSnoop logs
    <br />
    <a href="#documentation"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/NullString1/BTLeTool/issues">Report Bug</a>
    ·
    <a href="https://github.com/NullString1/BTLeTool/issues">Request Feature</a>
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#about-the-project">About The Project</a></li>
    <li><a href="#features">Features</a></li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#supported-packet-types">Supported Packet Types</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
  </ol>
</details>

## About The Project

BTLeTool is an Android application designed for analyzing Bluetooth Low Energy communications captured in BTSnoop log files. It provides a user-friendly interface to view and analyze BLE packets, making it easier to debug and reverse engineer BLE communications on Android devices. 

BTLeTool uses the [btsnoop_parser](https://github.com/nullstring1/btsnoop_parser) library

## Features

✨ **Intuitive User Interface**
- Clean Material Design 3 interface
- Drag and drop packet reordering
- Swipe to delete with undo functionality
- Dark mode support

📱 **Android Integration**
- Native Android file picker integration
- Support for standard BTSnoop log files
- Real-time packet parsing and display

🔍 **Comprehensive Protocol Support**
- HCI (Host Controller Interface)
- L2CAP (Logical Link Control and Adaptation Protocol)
- ATT (Attribute Protocol) packet parsing
- MAC address tracking and display
- Packet direction indicators

## Getting Started

### Prerequisites

- Android Studio Electric Eel or newer
- Android SDK 34 or higher
- JDK 11 or higher
- Android device running Android 14 (API 34) or higher

### Installation

1. Clone the repository:
```bash
git clone https://github.com/NullString1/BTLeTool.git
```

2. Open the project in Android Studio

3. Build and run:
```bash
./gradlew assembleDebug
```

## Usage

1. Install the app on your Android device
2. Enable developer options and Bluetooth HCI snoop logging on your device
3. After capturing some Bluetooth traffic, open developer settings again and export a bug report
4. Wait for the bug report to finish, click from notification and share to yourself, then extract `btsnoop_hci.log` from `(zip)/FS/data/log/bt/`
5. Run BTLeTool
4. Tap "Open BTSnoop Log" and select your captured log file
5. View and analyze the captured packets:
   - Each packet shows timestamp, direction, and data
   - Drag packets to reorder them
   - Swipe to remove unwanted packets
   - View MAC addresses of connected devices

## Supported Packet Types

### HCI Packet Types
- Commands (0x01) - No parsing yet
- Events (0x04) - Currently only parsed for connection handle tracking to display MAC address of packets
- ACL Data (0x02) - Parsed to show GATT messages
- SCO Data (0x03) - No parsing yet

### ATT Commands
- Write Command (0x52) - Parsed as writing to GATT characteristic
- Handle Value Notification (0x1B) - Parsed as message back from other device

### Packet Information
- Timestamp
- Direction (sent/received)
- MAC addresses
- Raw packet data
- L2CAP and ATT headers

## Contributing

Contributions are welcome! Here's how you can help:

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

Distributed under the MIT License. See `LICENSE` for more information.

<!-- MARKDOWN LINKS & IMAGES -->
[license-shield]: https://img.shields.io/github/license/NullString1/BTLeTool.svg?style=for-the-badge
[license-url]: https://github.com/NullString1/BTLeTool/blob/master/LICENSE