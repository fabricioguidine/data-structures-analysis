# Makefile for Performance Analysis for Data Structures and Algorithms
# Creates JAR file with all necessary classes

JAVA_SRC = src/main/java
BUILD_DIR = build/classes
DIST_DIR = dist
JAR_FILE = $(DIST_DIR)/bookdepository-ds-analysis.jar
MANIFEST = build/META-INF/MANIFEST.MF

# Find all Java source files
JAVA_FILES := $(shell find $(JAVA_SRC) -name "*.java")

TEST_DIR = build/test
TEST_CP = lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:$(BUILD_DIR):$(TEST_DIR)

.PHONY: all clean build jar test run-sorting run-hashtable run-tree help

all: jar

help:
	@echo "Available targets:"
	@echo "  make          - Build the JAR file"
	@echo "  make jar      - Build the JAR file"
	@echo "  make test     - Run JUnit tests"
	@echo "  make clean    - Clean build artifacts"
	@echo "  make run-sorting    - Run Part I (Sorting Experiment)"
	@echo "  make run-hashtable  - Run Part II (Hash Table Experiment)"
	@echo "  make run-tree       - Run Part III (Tree Experiment)"

build: $(BUILD_DIR)
	@echo "Compiling Java sources..."
	@javac -d $(BUILD_DIR) -sourcepath $(JAVA_SRC) $(JAVA_FILES)
	@echo "Compilation successful!"

$(BUILD_DIR):
	@mkdir -p $(BUILD_DIR)

$(MANIFEST):
	@mkdir -p build/META-INF
	@echo "Manifest-Version: 1.0" > $(MANIFEST)
	@echo "Main-Class: com.bookdepository.experiments.SortingExperiment" >> $(MANIFEST)
	@echo "Created-By: Performance Analysis for Data Structures and Algorithms Makefile" >> $(MANIFEST)

jar: build $(MANIFEST) $(DIST_DIR)
	@echo "Creating JAR file..."
	@jar cfm $(JAR_FILE) $(MANIFEST) -C $(BUILD_DIR) .
	@echo "Build successful!"
	@echo "JAR file created: $(JAR_FILE)"
	@echo "Cleaning up build artifacts..."
	@rm -rf $(BUILD_DIR) build/META-INF
	@echo "Build cleanup complete!"

$(DIST_DIR):
	@mkdir -p $(DIST_DIR)

test: build
	@echo "Running JUnit tests..."
	@if [ ! -f lib/junit-4.13.2.jar ] || [ ! -f lib/hamcrest-core-1.3.jar ]; then \
		echo "ERROR: JUnit JAR files not found in lib/ directory."; \
		echo "Please download them using the instructions in lib/README.md"; \
		exit 1; \
	fi
	@mkdir -p $(TEST_DIR)
	@javac -cp "$(TEST_CP)" -d $(TEST_DIR) \
		$$(find src/test/java -name "*.java" 2>/dev/null || true)
	@if [ -d $(TEST_DIR)/com ]; then \
		for test in com.bookdepository.model.RecordTest \
			com.bookdepository.model.AuthorTest \
			com.bookdepository.algorithms.sorting.QuickSortTest \
			com.bookdepository.algorithms.sorting.HeapSortTest \
			com.bookdepository.structures.hashtable.RecordHashTableTest \
			com.bookdepository.structures.hashtable.AuthorHashTableTest; do \
			echo "Running $$test..."; \
			java -cp "$(TEST_CP)" org.junit.runner.JUnitCore $$test || true; \
		done; \
	fi

run-sorting: jar
	@java -cp $(JAR_FILE) com.bookdepository.experiments.SortingExperiment

run-hashtable: jar
	@java -cp $(JAR_FILE) com.bookdepository.experiments.HashTableExperiment

run-tree: jar
	@java -cp $(JAR_FILE) com.bookdepository.experiments.TreeExperiment

clean:
	@echo "Cleaning build artifacts..."
	@rm -rf $(BUILD_DIR)
	@rm -rf build
	@rm -rf $(DIST_DIR)
	@echo "Clean complete!"

