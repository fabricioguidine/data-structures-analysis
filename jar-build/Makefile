# Makefile for JAR Generation
# Performance Analysis for Data Structures and Algorithms

JAVA_SRC = ../src/main/java
BUILD_DIR = ../build/classes
DIST_DIR = ../dist
JAR_FILE = $(DIST_DIR)/bookdepository-ds-analysis.jar
MANIFEST = ../build/META-INF/MANIFEST.MF

# Find all Java source files
JAVA_FILES := $(shell find $(JAVA_SRC) -name "*.java")

.PHONY: all clean build jar help

all: jar

help:
	@echo "JAR Build Targets:"
	@echo "  make          - Build the JAR file"
	@echo "  make jar      - Build the JAR file"
	@echo "  make clean    - Clean build artifacts"
	@echo ""
	@echo "JAR will be created at: $(JAR_FILE)"

build: $(BUILD_DIR)
	@echo "Compiling Java sources..."
	@javac -d $(BUILD_DIR) -sourcepath $(JAVA_SRC) $(JAVA_FILES)
	@if [ $$? -ne 0 ]; then \
		echo "Compilation failed!"; \
		exit 1; \
	fi
	@echo "Compilation successful!"

$(BUILD_DIR):
	@mkdir -p $(BUILD_DIR)

$(MANIFEST):
	@mkdir -p ../build/META-INF
	@echo "Manifest-Version: 1.0" > $(MANIFEST)
	@echo "Main-Class: com.bookdepository.experiments.SortingExperiment" >> $(MANIFEST)
	@echo "Created-By: Performance Analysis for Data Structures and Algorithms" >> $(MANIFEST)

jar: build $(MANIFEST) $(DIST_DIR)
	@echo "Creating JAR file..."
	@jar cfm $(JAR_FILE) $(MANIFEST) -C $(BUILD_DIR) .
	@if [ $$? -ne 0 ]; then \
		echo "JAR creation failed!"; \
		exit 1; \
	fi
	@echo "Build successful!"
	@echo "JAR file created: $(JAR_FILE)"
	@echo "Cleaning up build artifacts..."
	@rm -rf $(BUILD_DIR) ../build/META-INF
	@echo "Build cleanup complete!"

$(DIST_DIR):
	@mkdir -p $(DIST_DIR)

clean:
	@echo "Cleaning build artifacts..."
	@rm -rf $(BUILD_DIR)
	@rm -rf ../build
	@rm -f $(JAR_FILE)
	@echo "Clean complete!"

