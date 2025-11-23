@echo off
REM JAR Build Script for Performance Analysis for Data Structures and Algorithms (Windows)

cd /d %~dp0

echo Building JAR for Performance Analysis for Data Structures and Algorithms...

REM Create build directories
mkdir build\classes 2>nul
mkdir dist 2>nul

REM Compile all Java files
echo Compiling Java sources...
javac -d ..\build\classes -sourcepath ..\src\main\java ^
    ..\src\main\java\com\bookdepository\model\*.java ^
    ..\src\main\java\com\bookdepository\io\*.java ^
    ..\src\main\java\com\bookdepository\algorithms\sorting\*.java ^
    ..\src\main\java\com\bookdepository\structures\hashtable\*.java ^
    ..\src\main\java\com\bookdepository\structures\tree\redblack\*.java ^
    ..\src\main\java\com\bookdepository\structures\tree\bplustree\*.java ^
    ..\src\main\java\com\bookdepository\experiments\*.java

if errorlevel 1 (
    echo Compilation failed!
    exit /b 1
)

echo Compilation successful!

REM Create manifest file
echo Creating manifest file...
mkdir ..\build\META-INF 2>nul
(
echo Manifest-Version: 1.0
echo Main-Class: com.bookdepository.experiments.SortingExperiment
echo Created-By: Performance Analysis for Data Structures and Algorithms Build Script
) > ..\build\META-INF\MANIFEST.MF

REM Create JAR file
echo Creating JAR file...
jar cfm ..\dist\bookdepository-ds-analysis.jar ..\build\META-INF\MANIFEST.MF -C ..\build\classes .

if errorlevel 1 (
    echo JAR creation failed!
    exit /b 1
)

echo Build successful!
echo JAR file created: ..\dist\bookdepository-ds-analysis.jar
echo.
echo To run experiments from JAR:
echo   java -cp ..\dist\bookdepository-ds-analysis.jar com.bookdepository.experiments.SortingExperiment
echo   java -cp ..\dist\bookdepository-ds-analysis.jar com.bookdepository.experiments.HashTableExperiment
echo   java -cp ..\dist\bookdepository-ds-analysis.jar com.bookdepository.experiments.TreeExperiment
echo.
echo Cleaning up build artifacts...
rmdir /s /q ..\build 2>nul
echo Build cleanup complete!

