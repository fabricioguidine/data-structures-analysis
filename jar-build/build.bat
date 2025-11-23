@echo off
REM JAR Build Script using Maven for Performance Analysis Project
REM This script builds the JAR using Maven and copies it to the project root

cd /d %~dp0\..

echo Building JAR for Performance Analysis for Data Structures and Algorithms...
echo Using Maven to build executable JAR with dependencies...
echo.

REM Build JAR using Maven
call mvn clean package -DskipTests

if errorlevel 1 (
    echo.
    echo Maven build failed! Make sure Maven is installed and configured.
    echo Check that all required model classes exist in src/main/java
    exit /b 1
)

echo.
echo Build successful!
echo.

REM Copy the JAR to project root
if exist "target\bookdepository-ds-analysis.jar" (
    copy /Y "target\bookdepository-ds-analysis.jar" "bookdepository-ds-analysis.jar"
    echo JAR file copied to project root: bookdepository-ds-analysis.jar
) else if exist "target\bookdepository-ds-analysis-jar-with-dependencies.jar" (
    copy /Y "target\bookdepository-ds-analysis-jar-with-dependencies.jar" "bookdepository-ds-analysis.jar"
    echo JAR file copied to project root: bookdepository-ds-analysis.jar
) else (
    echo Warning: JAR file not found in target directory
    echo Check target/ directory for available JAR files
)

echo.
echo To run experiments from JAR:
echo   java -jar bookdepository-ds-analysis.jar
echo.
echo Build complete!
