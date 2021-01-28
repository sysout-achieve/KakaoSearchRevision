#!/bin/bash
echo "kakao.api.key=\"$KAKAO_API_KEY\"" >> "./key.properties"
./gradlew clean build