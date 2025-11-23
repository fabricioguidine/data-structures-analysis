# Test Results Directory

This directory contains test results and analysis from running the JUnit test suite.

## Running Tests

To run all JUnit tests:

### Linux/Mac/Git Bash:
```bash
make test
# or
./test.sh
```

### Windows:
```bash
test.bat
```

### Manual Test Execution:
```bash
# Compile test classes
javac -cp "lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:build/classes" \
    -d build/test \
    src/test/java/com/bookdepository/**/*.java

# Run tests
java -cp "lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:build/classes:build/test" \
    org.junit.runner.JUnitCore com.bookdepository.model.RecordTest
```

## Test Structure

Tests are organized to match the source code structure:

- `src/test/java/com/bookdepository/model/` - Tests for model classes
- `src/test/java/com/bookdepository/algorithms/` - Tests for algorithms
- `src/test/java/com/bookdepository/structures/` - Tests for data structures
- `src/test/java/com/bookdepository/io/` - Tests for I/O utilities
- `src/test/java/com/bookdepository/experiments/` - Tests for experiment classes

## Test Results

Test results and analysis are stored in subdirectories:

- `results/` - Overall test execution results
- `sorting/` - Results from sorting algorithm tests
- `hashtable/` - Results from hash table tests
- `trees/` - Results from tree structure tests

## Expected Results

After running tests, you should see:
- All model tests passing
- All algorithm tests passing
- All data structure tests passing
- I/O utility tests passing

Each test validates:
- Correctness of operations
- Edge cases (null, empty, single element)
- Performance tracking
- Data integrity
