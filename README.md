# Smart Hospital IoT Manager

![Java](https://img.shields.io/badge/Java-17+-orange?logo=java)
![Build](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)

## Overview
Smart Hospital IoT Manager is a Java graphical application simulating a connected hospital environment.  
It allows visualization, configuration, and monitoring of medical IoT devices, built as part of the POO course at EPISEN.

---

## Supported Devices
- Connected pill dispenser  
- Blood pressure monitor  
- Smart scale  
- Oximeter  
- Glucometer  
- ECG Holter  

---

## Features
- Graphical interface with Java Swing/JavaFX  
- Device configuration (parameters & thresholds)  
- Alarm notifications when abnormal values are detected  
- Simulation mode with random medical data  
- Extensible design for adding new IoT devices  

---

## Installation & Execution
### Prerequisites
- Java JDK 17+  
- (Optional) Maven or Gradle  

### Steps
```bash
# Clone the repository
git clone https://github.com/your-username/smart-hospital-iot.git

# Compile sources
javac -d bin src/**/*.java

# Run the application
java -cp bin Main
