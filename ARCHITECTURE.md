# Project Architecture

This document describes the architectural structure and organization of the Book Depository Data Structures Analysis project.

## Directory Structure

```
src/main/java/com/bookdepository/
├── model/                      # Data models and domain objects
│   ├── Author.java            # Author entity
│   └── Record.java            # Book record entity
│
├── algorithms/                 # Algorithm implementations
│   └── sorting/               # Sorting algorithms
│       ├── HeapSort.java      # Heap sort implementation
│       └── QuickSort.java     # Quick sort implementation
│
├── structures/                 # Data structure implementations
│   ├── hashtable/             # Hash table implementations
│   │   ├── AuthorHashTable.java
│   │   └── RecordHashTable.java
│   └── tree/                  # Tree structure implementations
│       ├── RedBlackTree.java  # Red-Black tree (to be implemented)
│       └── BPlusTree.java     # B+ tree (to be implemented)
│
├── experiments/                # Benchmarking and experimental applications
│   ├── SortingExperiment.java      # Sorting algorithm performance tests
│   ├── HashTableExperiment.java   # Hash table performance tests
│   └── TreeExperiment.java         # Tree structure performance tests
│
├── io/                        # Input/Output utilities
│   ├── FileReader.java        # File reading utilities
│   ├── OutputFileWriter.java  # Generic output file writer
│   ├── PerformanceResult.java # Performance result data class
│   └── Part2OutputWriter.java # Specialized output writer for Part 2
│
└── utils/                     # Utility classes and helper functions
    └── MathUtils.java         # Mathematical utilities (primes, powers of 2)
```

## Package Organization Principles

### 1. **Model Package** (`com.bookdepository.model`)
- Contains domain entities and data models
- Pure data classes with minimal business logic
- Represents the core domain objects: `Record`, `Author`

### 2. **Algorithms Package** (`com.bookdepository.algorithms`)
- Organized by algorithm type (e.g., `sorting`, `searching`)
- Each algorithm is self-contained and reusable
- Algorithms are stateless where possible
- Performance metrics (comparisons, swaps) are tracked internally

### 3. **Structures Package** (`com.bookdepository.structures`)
- Organized by structure type (e.g., `hashtable`, `tree`)
- Implements common data structures with performance tracking
- Each structure is a self-contained implementation
- Follows standard data structure interfaces where applicable

### 4. **Experiments Package** (`com.bookdepository.experiments`)
- Contains benchmarking and experimental applications
- Each experiment focuses on a specific aspect:
  - `SortingExperiment`: Compares sorting algorithm performance
  - `HashTableExperiment`: Tests hash table functionality and performance
  - `TreeExperiment`: Tests tree structure performance
- Experiments are independent and can be run separately

### 5. **IO Package** (`com.bookdepository.io`)
- Handles all file input/output operations
- Provides utilities for reading CSV files and input files
- Handles output formatting and writing

### 6. **Utils Package** (`com.bookdepository.utils`)
- Contains utility functions used across the project
- Mathematical utilities, helper functions, etc.

## Design Principles

### Separation of Concerns
- **Data Models**: Pure domain entities
- **Algorithms**: Reusable algorithm implementations
- **Data Structures**: Reusable data structure implementations
- **Experiments**: Application logic that uses algorithms and structures
- **IO**: File handling and data persistence
- **Utils**: Cross-cutting utilities

### Modularity
- Each package has a clear, single responsibility
- Dependencies flow in one direction: experiments → structures/algorithms → model → io/utils
- No circular dependencies

### Reusability
- Algorithms and structures are independent and can be used in different contexts
- Model classes are shared across all experiments
- IO utilities are generic and reusable

### Testability
- Each component can be tested independently
- Clear interfaces between components
- Minimal coupling between modules

## Running Experiments

### Sorting Experiment
```bash
java com.bookdepository.experiments.SortingExperiment
```
Tests sorting algorithms (QuickSort, HeapSort) with various input sizes.

### Hash Table Experiment
```bash
java com.bookdepository.experiments.HashTableExperiment
```
Tests hash table operations and finds most frequent authors.

### Tree Experiment
```bash
java com.bookdepository.experiments.TreeExperiment
```
Tests tree structures (Red-Black Tree, B+ Tree) performance.

## Future Extensibility

The architecture is designed to easily accommodate:
- New sorting algorithms (add to `algorithms.sorting`)
- New data structures (add to `structures.tree` or new subpackage)
- New experiments (add to `experiments`)
- New data models (add to `model`)

## Dependencies Flow

```
experiments
    ↓
    ├──→ algorithms (sorting, searching)
    ├──→ structures (hashtable, tree)
    ├──→ model (Record, Author)
    └──→ io (FileReader, OutputWriter)
         ↓
         utils (MathUtils, helpers)
```

This ensures a clean dependency hierarchy and makes the codebase maintainable and testable.

