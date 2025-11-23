# Build Instructions - Performance Analysis for Data Structures and Algorithms

## Quick Start

### Build JAR File

**Linux/Mac/Git Bash:**
```bash
make
# or
./build.sh
```

**Windows:**
```bash
build.bat
```

**Output:** `dist/bookdepository-ds-analysis.jar`

## Detailed Build Process

### Prerequisites

- **Java Development Kit (JDK)** 8 or higher
- Verify installation: `java -version` and `javac -version`

### Build Steps

1. **Create build directories:**
   ```bash
   mkdir -p build/classes dist
   ```

2. **Compile Java sources:**
   ```bash
   javac -d build/classes -sourcepath src/main/java \
       src/main/java/com/bookdepository/model/*.java \
       src/main/java/com/bookdepository/io/*.java \
       src/main/java/com/bookdepository/algorithms/sorting/*.java \
       src/main/java/com/bookdepository/structures/hashtable/*.java \
       src/main/java/com/bookdepository/structures/tree/redblack/*.java \
       src/main/java/com/bookdepository/structures/tree/bplustree/*.java \
       src/main/java/com/bookdepository/experiments/*.java
   ```

3. **Create manifest file:**
   ```bash
   mkdir -p build/META-INF
   echo "Manifest-Version: 1.0" > build/META-INF/MANIFEST.MF
   echo "Main-Class: com.bookdepository.experiments.SortingExperiment" >> build/META-INF/MANIFEST.MF
   echo "Created-By: Performance Analysis for Data Structures and Algorithms" >> build/META-INF/MANIFEST.MF
   ```

4. **Create JAR file:**
   ```bash
   jar cfm dist/bookdepository-ds-analysis.jar build/META-INF/MANIFEST.MF -C build/classes .
   ```

## Running from JAR

```bash
# Part I: Sorting Algorithms
java -cp dist/bookdepository-ds-analysis.jar com.bookdepository.experiments.SortingExperiment

# Part II: Hash Tables
java -cp dist/bookdepository-ds-analysis.jar com.bookdepository.experiments.HashTableExperiment

# Part III: Tree Structures
java -cp dist/bookdepository-ds-analysis.jar com.bookdepository.experiments.TreeExperiment
```

## Clean Build

To remove all build artifacts:

```bash
make clean
# or manually
rm -rf build dist
```

## Troubleshooting

### ClassNotFoundException

Ensure all dependencies are compiled. The build script compiles all classes in the correct order.

### Manifest Issues

The manifest file is created automatically by the build scripts. Ensure the MANIFEST.MF file ends with a newline.

### Windows Path Issues

On Windows, use backslashes or forward slashes consistently. The `build.bat` script handles Windows paths correctly.

