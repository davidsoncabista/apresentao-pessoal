#!/java/bin/bash
#
# Copyright 2007-2022 the original author or authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#


# -----------------------------------------------------------------------------
# Maven Start Up Script
#
# Required ENV vars:
# ------------------
#   JAVA_HOME - location of a JDK home dir
#
# Optional ENV vars
# -----------------
#   MAVEN_OPTS - parameters passed to the Java VM when running Maven
#     e.g. to debug Maven itself, use
#       set MAVEN_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000"
#   MAVEN_SKIP_RC - flag to disable loading of /etc/mavenrc and ~/.mavenrc
#     e.g. MAVEN_SKIP_RC=true
# -----------------------------------------------------------------------------

MAVEN_PROJECT_DIR="$(cd "$(dirname "$0")" && pwd)"

MAVEN_WRAPPER_JAR_DIR="${MAVEN_PROJECT_DIR}/.mvn/wrapper"
MAVEN_WRAPPER_JAR_PATH="${MAVEN_WRAPPER_JAR_DIR}/maven-wrapper.jar"
MAVEN_WRAPPER_PROPERTIES_PATH="${MAVEN_WRAPPER_JAR_DIR}/maven-wrapper.properties"

if [ -z "$JAVA_HOME" ]; then
  # Fallback to current running java
  JAVA_HOME="$(dirname "$(dirname "$(which java)")")"
fi

# Determine the Java command to use to start the JVM.
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        # IBM's JDK on AIX uses strange locations for the executables
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
    if [ ! -x "$JAVACMD" ] ; then
        die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
    fi
else
    JAVACMD="java"
    which java >/dev/null 2>&1 || die "ERROR: JAVA_HOME is not set and no \'java\' command could be found in your PATH.

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
fi

download_wrapper() {
    if [ -f "$MAVEN_WRAPPER_PROPERTIES_PATH" ]; then
        # Read the wrapper properties file, and convert all backslashes to forward slashes.
        # This makes it possible to have the distributionUrl containing backslashes (and not having to escape them).
        while IFS= read -r line || [ -n "$line" ] ; do
            case $line in
                distributionUrl=*)
                    MAVEN_WRAPPER_URL="${line#*=}"
                    break
                    ;;
            esac
        done < "$MAVEN_WRAPPER_PROPERTIES_PATH"

        # If the distribution URL is not specified, there's nothing to download.
        if [ -n "$MAVEN_WRAPPER_URL" ]; then
            local MAVEN_WRAPPER_DOWNLOAD_DIR="${MAVEN_WRAPPER_JAR_DIR}"
            local MAVEN_WRAPPER_DOWNLOAD_PATH="${MAVEN_WRAPPER_DOWNLOAD_DIR}/maven-wrapper.jar.part"
            local MAVEN_WRAPPER_TMP_PATH="${MAVEN_WRAPPER_JAR_DIR}/maven-wrapper.jar.tmp"

            echo "Downloading $MAVEN_WRAPPER_URL"
            mkdir -p "$MAVEN_WRAPPER_DOWNLOAD_DIR"

            if [ -n "$(which curl)" ]; then
                curl --fail --silent --location --output "$MAVEN_WRAPPER_DOWNLOAD_PATH" "$MAVEN_WRAPPER_URL"
            elif [ -n "$(which wget)" ]; then
                wget --quiet -O "$MAVEN_WRAPPER_DOWNLOAD_PATH" "$MAVEN_WRAPPER_URL"
            else
                die "Could not find curl or wget. Please install one of these and try again."
            fi

            # Keep the tmp file in case the download fails and the user wants to resume.
            mv "$MAVEN_WRAPPER_DOWNLOAD_PATH" "$MAVEN_WRAPPER_TMP_PATH"
            mv "$MAVEN_WRAPPER_TMP_PATH" "$MAVEN_WRAPPER_JAR_PATH"
        fi
    fi
}

# If the maven-wrapper.jar does not exist, download it.
if [ ! -f "$MAVEN_WRAPPER_JAR_PATH" ]; then
    download_wrapper
fi

exec "$JAVACMD" \
  $MAVEN_OPTS \
  -classpath "$MAVEN_WRAPPER_JAR_PATH" \
  "-Dmaven.home=${MAVEN_HOME}" \
  "-Dmaven.multiModuleProjectDirectory=${MAVEN_PROJECT_DIR}" \
  org.apache.maven.wrapper.MavenWrapperMain "$@"