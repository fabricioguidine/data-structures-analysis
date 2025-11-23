# JUnit Dependencies

This directory should contain the JUnit JAR files required for running tests.

## Required JAR Files

Download the following JAR files and place them in this directory:

1. **junit-4.13.2.jar** - JUnit 4 framework
   - Download from: https://repo1.maven.org/maven2/junit/junit/4.13.2/junit-4.13.2.jar

2. **hamcrest-core-1.3.jar** - Matcher library (required by JUnit)
   - Download from: https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar

## Quick Download (Linux/Mac)

```bash
cd lib
curl -O https://repo1.maven.org/maven2/junit/junit/4.13.2/junit-4.13.2.jar
curl -O https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar
```

## Quick Download (Windows PowerShell)

```powershell
cd lib
Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/junit/junit/4.13.2/junit-4.13.2.jar" -OutFile "junit-4.13.2.jar"
Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar" -OutFile "hamcrest-core-1.3.jar"
```

## Alternative: Using Maven

If you have Maven installed, you can automatically download dependencies:

```bash
mvn dependency:copy-dependencies -DoutputDirectory=lib
```

## Note

The JUnit JAR files should be committed to the repository for easy testing setup.

