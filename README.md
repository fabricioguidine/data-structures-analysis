# Performance Analysis for Data Structures and Algorithms

> A comprehensive performance analysis of fundamental data structures and algorithms using the Book Depository dataset

[![Java](https://img.shields.io/badge/Java-8%2B-orange.svg)](https://www.oracle.com/java/)
[![Python](https://img.shields.io/badge/Python-3.7%2B-blue.svg)](https://www.python.org/)
[![License](https://img.shields.io/badge/License-Academic-blue.svg)](LICENSE)

This project provides a comprehensive performance analysis of fundamental data structures and algorithms using the [Book Depository dataset](https://www.kaggle.com/sp1thas/book-depository-dataset) from Kaggle. It implements and empirically evaluates sorting algorithms (QuickSort, HeapSort), hash tables, and balanced tree structures (Red-Black Tree, B+ Trees) with real-world book and author data.

## 🎯 Overview

This repository contains three main experimental parts that analyze different aspects of data structures:

- **Part I**: Sorting algorithm performance analysis (QuickSort and HeapSort)
- **Part II**: Most frequent authors identification using hash tables
- **Part III**: Balanced tree structures performance evaluation (Red-Black Tree, B+ Trees)

## 📋 Table of Contents

- [Prerequisites](#-prerequisites)
- [Getting Started](#-getting-started)
  - [Dataset Setup](#dataset-setup)
  - [Project Structure](#project-structure)
  - [Building the Project](#building-the-project)
- [Usage](#-usage)
- [JAR File](#-jar-file)
  - [Part I: Sorting Algorithms](#part-i-sorting-algorithms)
  - [Part II: Hash Tables](#part-ii-hash-tables)
  - [Part III: Tree Structures](#part-iii-tree-structures)
- [Project Structure](#-project-structure)
- [Documentation](#-documentation)
- [Contributing](#-contributing)
- [Authors](#-authors)
- [License](#-license)

## 🔧 Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)** 8 or higher
  - Verify installation: `java -version`
- **Python** 3.7 or higher
  - Verify installation: `python --version`
- **Kaggle API** credentials for dataset access
  - Sign up at [Kaggle](https://www.kaggle.com/)
  - Follow [API setup instructions](https://www.kaggle.com/docs/api)

## 🚀 Getting Started

### Dataset Setup

The dataset is available on [Kaggle](https://www.kaggle.com/sp1thas/book-depository-dataset). Download it using the provided Python script:

**Step 1:** Install Python dependencies

```bash
pip install -r scripts/requirements.txt
```

**Step 2:** Configure Kaggle API credentials

Place your `kaggle.json` file in:
- **Windows**: `C:\Users\<username>\.kaggle\kaggle.json`
- **Linux/Mac**: `~/.kaggle/kaggle.json`

Or set environment variables:
```bash
export KAGGLE_USERNAME=your_username
export KAGGLE_KEY=your_api_key
```

**Step 3:** Download the dataset

```bash
python scripts/download_dataset.py
```

The script will download the dataset files to the `data/` directory:
- `authors.csv` - Author information
- `dataset_simp_sem_descricao.csv` - Book records

### Project Structure

```
.
├── data/                        # Dataset files (downloaded from Kaggle)
│   ├── authors.csv
│   └── dataset_simp_sem_descricao.csv
├── docs/                        # Documentation
│   ├── requirements.pdf        # Project requirements (PDF)
│   └── latex/                  # LaTeX technical report
│       ├── relatorio.tex       # Main LaTeX document
│       ├── referencias.bib     # Bibliography
│       └── Makefile            # Build automation
├── input/                       # Input files for experiments
│   ├── entrada.txt             # Test sizes configuration
│   └── README.md
├── output/                      # Output files from experiments
│   ├── saida.txt               # Part I results
│   ├── saidaPart2.txt          # Part II results
│   ├── saidaInsercao.txt       # Part III insertion results
│   ├── saidaBusca.txt          # Part III search results
│   └── README.md
├── scripts/                     # Utility scripts
│   ├── download_dataset.py     # Kaggle dataset downloader
│   └── requirements.txt        # Python dependencies
├── src/                         # Source code
│   └── main/
│       └── java/
│           └── com/bookdepository/
│               ├── model/           # Domain entities
│               │   ├── Record.java
│               │   └── Author.java
│               ├── algorithms/      # Algorithm implementations
│               │   └── sorting/
│               │       ├── QuickSort.java
│               │       └── HeapSort.java
│               ├── structures/      # Data structure implementations
│               │   ├── hashtable/
│               │   │   ├── RecordHashTable.java
│               │   │   └── AuthorHashTable.java
│               │   └── tree/
│               │       ├── redblack/
│               │       └── bplustree/
│               ├── experiments/     # Benchmarking applications
│               │   ├── SortingExperiment.java
│               │   ├── HashTableExperiment.java
│               │   └── TreeExperiment.java
│               ├── io/              # File I/O utilities
│               │   ├── FileReader.java
│               │   ├── OutputFileWriter.java
│               │   ├── Part2OutputWriter.java
│               │   └── PerformanceResult.java
│               └── utils/           # Utility functions
├── tests/                       # Test results and analysis
│   ├── sorting/
│   ├── hashtable/
│   └── trees/
├── .gitignore
├── README.md
└── LICENSE
```

### Building the Project

This project provides multiple build options to create a JAR file:

#### Option 1: Using Make (Linux/Mac/Git Bash)

```bash
make
```

This will compile all Java files and create `dist/bookdepository-ds-analysis.jar`.

#### Option 2: Using Build Script (Linux/Mac)

```bash
chmod +x build.sh
./build.sh
```

#### Option 3: Using Build Script (Windows)

```bash
build.bat
```

#### Option 4: Manual Compilation

```bash
# Create build directories
mkdir -p build/classes dist

# Compile all Java files
javac -d build/classes -sourcepath src/main/java \
    src/main/java/com/bookdepository/model/*.java \
    src/main/java/com/bookdepository/io/*.java \
    src/main/java/com/bookdepository/algorithms/sorting/*.java \
    src/main/java/com/bookdepository/structures/hashtable/*.java \
    src/main/java/com/bookdepository/structures/tree/redblack/*.java \
    src/main/java/com/bookdepository/structures/tree/bplustree/*.java \
    src/main/java/com/bookdepository/experiments/*.java

# Create manifest
mkdir -p build/META-INF
echo "Manifest-Version: 1.0" > build/META-INF/MANIFEST.MF
echo "Main-Class: com.bookdepository.experiments.SortingExperiment" >> build/META-INF/MANIFEST.MF

# Create JAR file
jar cfm dist/bookdepository-ds-analysis.jar build/META-INF/MANIFEST.MF -C build/classes .
```

**Output:** The JAR file will be created in `dist/bookdepository-ds-analysis.jar`

#### Running Experiments from JAR

After building, you can run experiments using the JAR file:

```bash
# Part I: Sorting Algorithms
java -cp dist/bookdepository-ds-analysis.jar com.bookdepository.experiments.SortingExperiment

# Part II: Hash Tables
java -cp dist/bookdepository-ds-analysis.jar com.bookdepository.experiments.HashTableExperiment

# Part III: Tree Structures
java -cp dist/bookdepository-ds-analysis.jar com.bookdepository.experiments.TreeExperiment
```

## 🧪 Testing

This project includes a comprehensive JUnit test suite to validate all implementations.

### Running Tests

**Linux/Mac/Git Bash:**
```bash
make test
# or
./test.sh
```

**Windows:**
```bash
test.bat
```

### Test Coverage

The test suite covers:
- **Model Classes**: `Record` and `Author` validation
- **Sorting Algorithms**: QuickSort and HeapSort correctness tests
- **Hash Tables**: `RecordHashTable` and `AuthorHashTable` functionality
- **Tree Structures**: Red-Black Tree and B+ Tree operations
- **I/O Utilities**: File reading and writing validation

### Test Requirements

Before running tests, download JUnit dependencies:

```bash
# Download JUnit JAR files to lib/ directory
# See lib/README.md for detailed instructions
```

**Quick Download (Linux/Mac):**
```bash
cd lib
curl -O https://repo1.maven.org/maven2/junit/junit/4.13.2/junit-4.13.2.jar
curl -O https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar
```

**Quick Download (Windows PowerShell):**
```powershell
cd lib
Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/junit/junit/4.13.2/junit-4.13.2.jar" -OutFile "junit-4.13.2.jar"
Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar" -OutFile "hamcrest-core-1.3.jar"
```

All test classes are located in `src/test/java/com/bookdepository/`. See `tests/README.md` for more information about the test structure and results.

## 💻 Usage

### Part I: Sorting Algorithms

Analyzes the performance of QuickSort and HeapSort algorithms on book records.

**Metrics measured:**
- Number of key comparisons
- Number of record copies/swaps
- Total execution time (milliseconds)

**Run the experiment:**

```bash
cd src/main/java
javac -d ../../../build com/bookdepository/experiments/SortingExperiment.java
java -cp ../../../build com.bookdepository.experiments.SortingExperiment
```

**Output:** Results are written to `output/saida.txt`

### Part II: Hash Tables

Implements hash tables to identify the most frequent authors in a set of books.

**Data structures used:**
- **RecordHashTable**: Hash table for records using open addressing with double hashing
- **AuthorHashTable**: Hash table for authors with frequency tracking

**Run the experiment:**

```bash
cd src/main/java
javac -d ../../../build com/bookdepository/experiments/HashTableExperiment.java
java -cp ../../../build com.bookdepository.experiments.HashTableExperiment
```

The program will prompt for the number `N` of top authors to display.

**Output:** Results are written to `output/saidaPart2.txt`

### Part III: Tree Structures

Evaluates the performance of balanced tree structures for insertion and search operations.

**Structures analyzed:**
- **Red-Black Tree**: Self-balancing binary search tree
- **B+ Tree (d=2)**: B+ tree with minimum degree 2
- **B+ Tree (d=20)**: B+ tree with minimum degree 20

**Performance metrics:**
- Insertion statistics (comparisons, swaps, time)
- Search statistics (comparisons, swaps, time)

**Run the experiment:**

```bash
cd src/main/java
javac -d ../../../build com/bookdepository/experiments/TreeExperiment.java
java -cp ../../../build com.bookdepository.experiments.TreeExperiment
```

**Output:** 
- Insertion results: `output/saidaInsercao.txt`
- Search results: `output/saidaBusca.txt`

### Input Format

Create `input/entrada.txt` with the following format:

```
N
value1
value2
...
valueN
```

Where `N` is the number of test cases, and each value represents the number of records to process.

**Example:**
```
5
1000
5000
10000
50000
100000
```

## 📁 Project Structure

### Package Organization

```
com.bookdepository/
├── model/              # Domain entities
├── algorithms/         # Reusable algorithms
│   └── sorting/        # Sorting algorithms
├── structures/         # Data structure implementations
│   ├── hashtable/      # Hash tables
│   └── tree/           # Tree structures
├── experiments/        # Benchmarking applications
├── io/                 # File I/O utilities
└── utils/              # Utility functions
```

### Key Components

- **Model Layer**: Domain entities (`Record`, `Author`)
- **Algorithms**: Sorting algorithms with performance tracking
- **Data Structures**: Hash tables and balanced trees
- **Experiments**: Benchmarking applications for each part
- **I/O Utilities**: File reading and output writing utilities

## 📚 Documentation

### Requirements

The complete project requirements are available in:
- **PDF**: `docs/requirements.pdf`

### Technical Report

A comprehensive technical report in LaTeX (using abnTeX2) analyzing all experimental results is located in `docs/latex/`:

- **Main document**: `docs/latex/relatorio.tex`
- **Bibliography**: `docs/latex/referencias.bib`
- **Compiled PDF**: `docs/latex/relatorio.pdf` (needs to be compiled)
- **Build instructions**: See `docs/latex/README.md` or `docs/latex/COMPILE_PDF.md`

The report analyzes results from the `tests/` directory, including:
- Sorting algorithm performance (`tests/sorting/`)
- Hash table experiments (`tests/hashtable/`)
- Tree structure benchmarks (`tests/trees/`)

**To compile the PDF report:**

```bash
cd docs/latex
make pdf
```

Or use Overleaf (online LaTeX editor): https://www.overleaf.com/

**Note:** The compiled PDF (`relatorio.pdf`) should be committed to the repository after compilation.

## 🤝 Contributing

This is an academic project for DCC012 - Data Structures II at Universidade Federal de Juiz de Fora. Contributions are welcome for bug fixes and improvements.

## 👥 Authors

- **Débora Duarte**
- **Fabrício Guidine**
- **Walkíria Garcia**

## 📄 License

This project is part of the coursework for **DCC012 - Data Structures II** at **Universidade Federal de Juiz de Fora**.

---

## 📊 Dataset Schema

Each book record contains the following fields:

| Field | Type | Description |
|-------|------|-------------|
| `authors` | `List<String>` | List of author IDs |
| `bestsellers_rank` | `int` | Bestseller ranking |
| `categories` | `List<String>` | Book categories |
| `edition` | `String` | Edition information |
| `id` | `String` | Unique book identifier |
| `isbn10` | `String` | ISBN-10 |
| `isbn13` | `String` | ISBN-13 |
| `rating_avg` | `float` | Average rating (0-5) |
| `rating_count` | `int` | Number of ratings |
| `title` | `String` | Book title |

## 🔗 Related Links

- [Kaggle Dataset](https://www.kaggle.com/sp1thas/book-depository-dataset)
- [Kaggle API Documentation](https://www.kaggle.com/docs/api)
- [Universidade Federal de Juiz de Fora](https://www.ufjf.br/)

---

**Note:** This project is for academic purposes as part of the Data Structures II course at UFJF.

## 📦 JAR File

A pre-built JAR file is available in `dist/bookdepository-ds-analysis.jar` for convenience.

**To rebuild the JAR file:**
1. Ensure Java JDK 8+ is installed
2. Run the build script:
   - **Linux/Mac/Git Bash**: `make` or `./build.sh`
   - **Windows**: `build.bat`
3. The JAR will be created in `dist/bookdepository-ds-analysis.jar`
4. After building, clean up build artifacts: `make clean` or delete the `build/` directory

**Running from JAR:**
```bash
# Part I: Sorting Algorithms
java -cp dist/bookdepository-ds-analysis.jar com.bookdepository.experiments.SortingExperiment

# Part II: Hash Tables
java -cp dist/bookdepository-ds-analysis.jar com.bookdepository.experiments.HashTableExperiment

# Part III: Tree Structures
java -cp dist/bookdepository-ds-analysis.jar com.bookdepository.experiments.TreeExperiment
```
