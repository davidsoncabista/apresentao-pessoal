@ECHO OFF

SETLOCAL

SET MAVEN_PROJECT_DIR=%~dp0

SET MAVEN_WRAPPER_JAR_DIR=%MAVEN_PROJECT_DIR%\.mvn\wrapper
SET MAVEN_WRAPPER_JAR_PATH=%MAVEN_WRAPPER_JAR_DIR%\maven-wrapper.jar
SET MAVEN_WRAPPER_PROPERTIES_PATH=%MAVEN_WRAPPER_JAR_DIR%\maven-wrapper.properties

IF NOT EXIST "%MAVEN_WRAPPER_PROPERTIES_PATH%" (
    ECHO Could not find %MAVEN_WRAPPER_PROPERTIES_PATH%
    EXIT /B 1
)

FOR /F "usebackq eol=# tokens=1,2 delims==" %%A IN ("%MAVEN_WRAPPER_PROPERTIES_PATH%") DO (
    IF "%%A"=="distributionUrl" (
        SET MAVEN_WRAPPER_URL=%%B
    )
)

IF NOT DEFINED MAVEN_WRAPPER_URL (
    ECHO distributionUrl was not found in %MAVEN_WRAPPER_PROPERTIES_PATH%
    EXIT /B 1
)

IF EXIST "%MAVEN_WRAPPER_JAR_PATH%" (
    GOTO :RUN_MAVEN
)

ECHO Downloading %MAVEN_WRAPPER_URL%

powershell.exe -Command "[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; (New-Object Net.WebClient).DownloadFile('%MAVEN_WRAPPER_URL%', '%MAVEN_WRAPPER_JAR_PATH%.part')"

IF EXIST "%MAVEN_WRAPPER_JAR_PATH%.part" (
    MOVE /Y "%MAVEN_WRAPPER_JAR_PATH%.part" "%MAVEN_WRAPPER_JAR_PATH%"
)

IF NOT EXIST "%MAVEN_WRAPPER_JAR_PATH%" (
    ECHO Failed to download %MAVEN_WRAPPER_URL%
    EXIT /B 1
)

:RUN_MAVEN

SET JAVA_HOME_STRING=Java home: %JAVA_HOME%

SET JAVA_EXE=%JAVA_HOME%\bin\java.exe

IF NOT EXIST "%JAVA_EXE%" (
    SET JAVA_EXE=java.exe
)

"%JAVA_EXE%" %MAVEN_OPTS% -classpath "%MAVEN_WRAPPER_JAR_PATH%" "-Dmaven.home=%MAVEN_HOME%" "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECT_DIR%" org.apache.maven.wrapper.MavenWrapperMain %*