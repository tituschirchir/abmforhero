#!/usr/bin/env bash
gradle clean;
gradle generateAvro
./gradlew build && java -jar build/libs/abm-0.1.jar