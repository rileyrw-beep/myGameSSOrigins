@echo off
echo ===================================
echo  Compiling Java Project
echo ===================================
echo.

REM Compile all Java files
javac -d bin src/*.java

REM Check if compilation succeeded
if %errorlevel%==0 (
    echo.
    echo Compilation successful!
    echo.
    echo ===================================
    echo  Running Main
    echo ===================================
    echo.
    java -cp bin Main
) else (
    echo.
    echo Compilation failed!
    pause
    exit /b 1
)

echo.
pause