# Distribution Directory

This directory contains the compiled JAR file for the Performance Analysis for Data Structures and Algorithms project.

## JAR File

- `bookdepository-ds-analysis.jar` - Complete application JAR file

## Building the JAR

To build the JAR file, run one of the following:

- **Make**: `make` or `make jar`
- **Linux/Mac**: `./build.sh`
- **Windows**: `build.bat`

See the main README.md for detailed build instructions.

## Running from JAR

After building, run experiments using:

```bash
# Part I: Sorting Algorithms
java -cp bookdepository-ds-analysis.jar com.bookdepository.experiments.SortingExperiment

# Part II: Hash Tables
java -cp bookdepository-ds-analysis.jar com.bookdepository.experiments.HashTableExperiment

# Part III: Tree Structures
java -cp bookdepository-ds-analysis.jar com.bookdepository.experiments.TreeExperiment
```

## Note

This directory is included in the repository to provide easy access to the compiled JAR file. The JAR file will be created after running the build scripts.

