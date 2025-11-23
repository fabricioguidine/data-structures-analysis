#!/bin/bash

# JAR Build Script using Maven for Performance Analysis Project
# This script builds the JAR using Maven and copies it to the project root

cd "$(dirname "$0")/.."

echo "Building JAR for Performance Analysis for Data Structures and Algorithms..."
echo "Using Maven to build executable JAR with dependencies..."
echo ""

# Build JAR using Maven
mvn clean package -DskipTests

if [ $? -ne 0 ]; then
    echo ""
    echo "Maven build failed! Make sure Maven is installed and configured."
    echo "Check that all required model classes exist in src/main/java"
    exit 1
fi

echo ""
echo "Build successful!"
echo ""

# Copy the JAR to project root
if [ -f "target/bookdepository-ds-analysis.jar" ]; then
    cp -f "target/bookdepository-ds-analysis.jar" "bookdepository-ds-analysis.jar"
    echo "JAR file copied to project root: bookdepository-ds-analysis.jar"
elif [ -f "target/bookdepository-ds-analysis-jar-with-dependencies.jar" ]; then
    cp -f "target/bookdepository-ds-analysis-jar-with-dependencies.jar" "bookdepository-ds-analysis.jar"
    echo "JAR file copied to project root: bookdepository-ds-analysis.jar"
else
    echo "Warning: JAR file not found in target directory"
    echo "Check target/ directory for available JAR files"
fi

echo ""
echo "To run experiments from JAR:"
echo "  java -jar bookdepository-ds-analysis.jar"
echo ""
echo "Build complete!"
