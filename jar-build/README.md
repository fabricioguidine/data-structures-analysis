# JAR Generation

This folder contains all scripts and tools for generating the project JAR file.

## Quick Start

### Linux/Mac/Git Bash:
```bash
cd jar-build
make
# or
./build.sh
```

### Windows:
```bash
cd jar-build
build.bat
```

**Output:** The JAR file will be created at `../dist/bookdepository-ds-analysis.jar`

## Available Scripts

- **`Makefile`** - Make-based build automation (Linux/Mac)
- **`build.sh`** - Bash build script (Linux/Mac/Git Bash)
- **`build.bat`** - Windows batch build script
- **`README.md`** - This file

## Requirements

- **Java Development Kit (JDK)** 8 or higher
- Verify installation: `java -version` and `javac -version`

## Building the JAR

The build process:
1. Compiles all Java source files from `../src/main/java/`
2. Creates a manifest file with the main class
3. Packages everything into a JAR file
4. Cleans up temporary build artifacts

## Using the JAR

After building, run experiments from the JAR:

```bash
# Part I: Sorting Algorithms
java -cp ../dist/bookdepository-ds-analysis.jar com.bookdepository.experiments.SortingExperiment

# Part II: Hash Tables
java -cp ../dist/bookdepository-ds-analysis.jar com.bookdepository.experiments.HashTableExperiment

# Part III: Tree Structures
java -cp ../dist/bookdepository-ds-analysis.jar com.bookdepository.experiments.TreeExperiment
```

## JAR File Location

The compiled JAR is located at: `../dist/bookdepository-ds-analysis.jar`

This JAR file should be committed to the repository for easy distribution.

