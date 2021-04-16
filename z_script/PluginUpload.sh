#!/usr/bin/env bash

echo Plugin build begin...


echo "// include ':basic',':spring-demo'"> settings.gradle
echo "include ':buildSrc'">> settings.gradle

./gradlew clean build :buildSrc:publishPlugins -PdryRun=false
./gradlew --stop

echo "include ':basic',':spring-demo'"> settings.gradle
echo "//include ':buildSrc'">> settings.gradle


echo Plugin build success...