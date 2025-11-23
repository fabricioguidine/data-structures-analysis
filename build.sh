#!/bin/bash

# Build script for Performance Analysis for Data Structures and Algorithms
# Creates JAR file with all necessary classes

echo "Building Performance Analysis for Data Structures and Algorithms..."

# Create build directories
mkdir -p build/classes
mkdir -p dist

# Compile all Java files
echo "Compiling Java sources..."
javac -d build/classes -sourcepath src/main/java \
    src/main/java/com/bookdepository/model/*.java \
    src/main/java/com/bookdepository/io/*.java \
    src/main/java/com/bookdepository/algorithms/sorting/*.java \
    src/main/java/com/bookdepository/structures/hashtable/*.java \
    src/main/java/com/bookdepository/structures/tree/redblack/*.java \
    src/main/java/com/bookdepository/structures/tree/bplustree/*.java \
    src/main/java/com/bookdepository/experiments/*.java

if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi

echo "Compilation successful!"

# Create manifest file
echo "Creating manifest file..."
mkdir -p build/META-INF
cat > build/META-INF/MANIFEST.MF << EOF
Manifest-Version: 1.0
Main-Class: com.bookdepository.experiments.SortingExperiment
Created-By: Performance Analysis for Data Structures and Algorithms Build Script
EOF

# Create JAR file
echo "Creating JAR file..."
jar cfm dist/bookdepository-ds-analysis.jar build/META-INF/MANIFEST.MF -C build/classes .

if [ $? -ne 0 ]; then
    echo "JAR creation failed!"
    exit 1
fi

echo "Build successful!"
echo "JAR file created: dist/bookdepository-ds-analysis.jar"
echo ""
echo "To run Part I (Sorting Experiment):"
echo "  java -cp dist/bookdepository-ds-analysis.jar com.bookdepository.experiments.SortingExperiment"
echo ""
echo "To run Part II (Hash Table Experiment):"
echo "  java -cp dist/bookdepository-ds-analysis.jar com.bookdepository.experiments.HashTableExperiment"
echo ""
echo "To run Part III (Tree Experiment):"
echo "  java -cp dist/bookdepository-ds-analysis.jar com.bookdepository.experiments.TreeExperiment"
echo ""
echo "Cleaning up build artifacts..."
rm -rf build
echo "Build cleanup complete!"

