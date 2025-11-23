# Test Scripts

This directory contains scripts for running the JUnit test suite.

## Available Scripts

- **`test.sh`** - Bash script for running tests (Linux/Mac/Git Bash)
- **`test.bat`** - Windows batch script for running tests

## Running Tests

### Linux/Mac/Git Bash:
```bash
cd tests/scripts
chmod +x test.sh
./test.sh
```

### Windows:
```bash
cd tests\scripts
test.bat
```

Or from the project root:
```bash
# Linux/Mac
./tests/scripts/test.sh

# Windows
tests\scripts\test.bat
```

## Requirements

Before running tests, ensure:
1. JUnit JAR files are in `../../lib/` directory
2. See `../../lib/README.md` for download instructions

## Test Coverage

The test suite validates:
- Model classes (Record, Author)
- Sorting algorithms (QuickSort, HeapSort)
- Hash tables (RecordHashTable, AuthorHashTable)
- Tree structures (RedBlackTree, BPlusTree)
- I/O utilities (FileReader, OutputFileWriter, Part2OutputWriter, PerformanceResult)

