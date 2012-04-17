
if "%ODDJOB_HOME%"=="" goto noOddjobHome

if NOT "%TRACAR_HOME%"=="" goto checkJava
set TRACAR_HOME=%~dp0..
cd "%TRACAR_HOME%"

:checkJava

set JAVA_CMD=java
if "%JAVA_HOME%"=="" goto launch
set JAVA_CMD="%JAVA_HOME%"\bin\java

:launch

%JAVA_CMD% "-Dtracar.home=%TRACAR_HOME%" -jar "%ODDJOB_HOME%"\run-oddjob.jar "%TRACAR_HOME%"\config\uninstall.oj.xml

exit /b %errorlevel%

:noOddjobHome

echo No ODDJOB_HOME.

exit /b 1

