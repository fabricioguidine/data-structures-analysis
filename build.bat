@echo off
REM Build script for Performance Analysis for Data Structures and Algorithms (Windows)
REM Creates JAR file with all necessary classes

echo Building Performance Analysis for Data Structures and Algorithms...

REM Create build directories
if not exist build\classes mkdir build\classes
if not exist dist mkdir dist

REM Compile all Java files
echo Compiling Java sources...
javac -d build\classes -sourcepath src\main\java src\main\java\com\bookdepository\model\*.java src\main\java\com\bookdepository\io\*.java src\main\java\com\bookdepository\algorithms\sorting\*.java src\main\java\com\bookdepository\structures\hashtable\*.java src\main\java\com\bookdepository\structures\tree\redblack\*.java src\main\java\com\bookdepository\structures\tree\bplustree\*.java src\main\java\com\bookdepository\experiments\*.java

if errorlevel 1 (
    echo Compilation failed!
    exit /b 1
)

echo Compilation successful!

REM Create manifest file
echo Creating manifest file...
if not exist build\META-INF mkdir build\META-INF
(
echo Manifest-Version: 1.0
echo Main-Class: com.bookdepository.experiments.SortingExperiment
echo Created-By: Performance Analysis for Data Structures and Algorithms Build Script
) > build\META-INF\MANIFEST.MF

REM Create JAR file
echo Creating JAR file...
jar cfm dist\bookdepository-ds-analysis.jar build\META-INF\MANIFEST.MF -C build\classes .

if errorlevel 1 (
    echo JAR creation failed!
    exit /b 1
)

echo.
echo Build successful!
echo JAR file created: dist\bookdepository-ds-analysis.jar
echo.
echo To run Part I (Sorting Experiment):
echo   java -cp dist\bookdepository-ds-analysis.jar com.bookdepository.experiments.SortingExperiment
echo.
echo To run Part II (Hash Table Experiment):
echo   java -cp dist\bookdepository-ds-analysis.jar com.bookdepository.experiments.HashTableExperiment
echo.
echo To run Part III (Tree Experiment):
echo   java -cp dist\bookdepository-ds-analysis.jar com.bookdepository.experiments.TreeExperiment
echo.
echo Cleaning up build artifacts...
if exist build rmdir /s /q build
echo Build cleanup complete!

