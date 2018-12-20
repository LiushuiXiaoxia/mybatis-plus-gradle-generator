#!/usr/bin/env bash

./PluginClean.sh

./gradlew clean :spring-demo:mpg --stacktrace
#./gradlew clean
./gradlew --stop