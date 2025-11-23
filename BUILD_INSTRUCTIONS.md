# Build Instructions

## Quick Build

To create the JAR file:

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

## Important Notes

1. **Java Required**: JDK 8 or higher must be installed and in your PATH
   - Verify: `java -version` and `javac -version`

2. **Build Artifacts**: After building, the build scripts automatically:
   - Create the JAR file in `dist/`
   - Clean up the `build/` directory
   - Keep only the JAR file and dist/README.md

3. **JAR File Location**: The compiled JAR will be at:
   - `dist/bookdepository-ds-analysis.jar`

4. **After Building**: The build directory is automatically cleaned up. Only the JAR file remains in `dist/`.

## Manual Build Cleanup

If you need to manually clean up after building:

```bash
# Using Make
make clean

# Or manually
rm -rf build dist/bookdepository-ds-analysis.jar
```

**Note:** The `dist/bookdepository-ds-analysis.jar` file should be committed to the repository for easy distribution.

## Troubleshooting

### Java Not Found
- Install JDK 8 or higher
- Ensure `java` and `javac` are in your PATH
- Windows: Add JDK bin directory to PATH environment variable

### Build Failures
- Check that all Java files are present in `src/main/java/`
- Ensure no compilation errors exist
- Try cleaning and rebuilding: `make clean && make`

