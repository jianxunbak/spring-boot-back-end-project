#!/bin/bash

# Update package list and install Maven
apt-get update -y
apt-get install -y maven

# Run the Maven build command
mvn clean package